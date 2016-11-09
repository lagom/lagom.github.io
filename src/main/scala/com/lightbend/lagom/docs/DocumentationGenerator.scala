package com.lightbend.lagom.docs

import java.io.File
import java.net.URL
import java.nio.file.{Files, StandardCopyOption}

import com.lightbend.docs.{Context, TOC}
import org.pegdown.{Extensions, LinkRenderer, PegDownProcessor, VerbatimSerializer}
import play.api.Logger
import play.api.libs.json.{Json, Reads}
import play.doc.PrettifyVerbatimSerializer
import play.twirl.api.{Html, Template1}
import play.utils.UriEncoding

import scala.collection.JavaConverters._
import scala.xml.XML

object DocumentationGenerator extends App {

  val devMode = sys.props.contains("dev")
  if (devMode) Logger.info("Running in dev mode")

  /*
   * CONFIGURATION
   */
  // Current documentation version
  val currentDocsVersion = "1.2.x"
  val currentLagomVersion = "1.2.0"

  //val baseUrl = "http://jroper.github.io/lagom.github.io"
  //val context = "/lagom.github.io"
  val baseUrl = "http://www.lagomframework.com"
  val context = ""


  // Templated pages to generate
  val templatePages: Seq[(String, Template1[LagomContext, Html])] = Seq(
    "index.html" -> html.index,
    "get-involved.html" -> html.getinvolved,
    "download.html" -> html.download
  )

  // Redirects
  val redirects: Seq[(String, String)] = Seq(
    "/documentation/index.html" -> s"$context/documentation/$currentDocsVersion/java/Home.html",
    "/documentation/java/index.html" -> s"$context/documentation/$currentDocsVersion/java/Home.html"
  )

  val activatorRelease = {
    val stream = new URL("https://www.lightbend.com/activator/latest").openStream()
    try {
      Json.parse(stream).as[ActivatorRelease]
    } finally {
      stream.close()
    }
  }

  val outputDir = new File(args(0))
  val docsDir = new File(args(1))
  val markdownDir = new File(args(2))
  val blogDir = new File(args(3))

  val pegdown = new PegDownProcessor(Extensions.ALL)
  def markdownToHtml(markdown: String) = {
    pegdown.markdownToHtml(markdown, new LinkRenderer,
      Map[String, VerbatimSerializer](VerbatimSerializer.DEFAULT -> PrettifyVerbatimSerializer).asJava)
  }

  val blogPosts = Blog.findBlogPosts(blogDir)
  val blogPostSummaries = blogPosts.map { post =>
    post -> Html(markdownToHtml(post.summary))
  }
  val blogPostTags = blogPosts.flatMap(_.tags).distinct.sorted
  val blogPostsByTag = blogPostTags.map(tag => tag -> blogPosts.filter(_.tags.contains(tag)))
  val blogSummary = {
    BlogSummary(blogPosts.take(3), blogPostsByTag.map {
      case (tag, posts) => tag -> posts.size
    })
  }

  implicit val lagomContext = LagomContext(baseUrl, context, currentLagomVersion, currentDocsVersion,
    activatorRelease, blogSummary)

  def generatePage(name: String, template: Template1[LagomContext, Html]): OutputFile = {
    savePage(name, template.render(lagomContext))
  }

  def generateRedirect(from: String, to: String): OutputFile = {
    savePage(from, html.redirect(to), includeInSitemap = false)
  }

  def savePage(name: String, rendered: Html, includeInSitemap: Boolean = true,
               sitemapPriority: String = "1.0"): OutputFile = {
    val file = new File(outputDir, name)
    file.getParentFile.mkdirs()
    Files.write(file.toPath, rendered.body.getBytes("utf-8"))
    val sitemapUrl = name match {
      case "index.html" => ""
      case index if index.endsWith("/index.html") => index.stripSuffix("/index.html")
      case other => other
    }
    OutputFile(file, sitemapUrl, includeInSitemap, sitemapPriority)
  }

  def renderMarkdownFiles(path: String, file: File): Seq[OutputFile] = {
    if (file.isDirectory) {
      new File(outputDir, path).mkdirs()
      file.listFiles().flatMap { child =>
        val childPath = if (path.isEmpty) child.getName else path + "/" + child.getName
        renderMarkdownFiles(childPath, child)
      }
    } else {
      val lines = Files.readAllLines(file.toPath).asScala.toList
      lines.dropWhile(!_.startsWith("#")) match {
        case title :: rest =>
          val strippedTitle = title.dropWhile(c => c == '#' || c == ' ')
          val rendered = markdownToHtml(rest.mkString("\n"))
          val page = html.markdown(strippedTitle, Html(rendered))
          Seq(savePage(path.replaceAll("\\.md$", ".html"), page))
        case Nil => throw new IllegalArgumentException("Markdown files must start with a heading using the # syntax")
      }
    }
  }

  // Blog
  val blogPostFiles = {
    val renderedBlogPosts = blogPosts.map { post =>
      val renderedPost = Html(markdownToHtml(post.markdown))
      post -> renderedPost
    }

    renderedBlogPosts.map {
      case (post, renderedPost) =>
        val page = html.blogPost(post, renderedPost)
        savePage(s"blog/${post.id}.html", page, sitemapPriority = "0.8")
    } ++ blogPostsByTag.map {
      // Tag pages
      case (tag, posts) =>
        val postSummaries = posts.flatMap{ post =>
          blogPostSummaries.find(_._1.id == post.id)
        }
        savePage(s"blog/tags/$tag.html", html.blog(s"Blog posts tagged with $tag", renderRecent = true, postSummaries))
    } :+ {
      // Index page
      savePage("blog/index.html", html.blog("Blog", renderRecent = false, blogPostSummaries.take(10)),
        sitemapPriority = "0.5")
    } :+ {
      // Feed
      val file = new File(outputDir, "blog/atom.xml")
      file.getParentFile.mkdirs()

      XML.save(file.getAbsolutePath, FeedFormatter.atom(renderedBlogPosts.take(10)), enc = "utf-8")

      OutputFile(file, "", includeInSitemap = false, sitemapPriority = "")
    }
  }

  // Discover versions
  val versions = docsDir.listFiles().toSeq.map { docsVersionDir =>
    val indexJson = new File(docsVersionDir, "index.json")

    val toc = Json.parse(Files.readAllBytes(indexJson.toPath)).as[TOC]
    docsVersionDir -> Version(docsVersionDir.getName, toc)
  }.sortBy(_._1.getName) // Will need a better sort in future

  val currentVersion = versions.find(_._2.name == currentDocsVersion)

  private def getNav(ctx: Context, acc: List[Section] = Nil): List[Section] = {
    ctx.parent match {
      case None => acc
      case Some(parent) =>
        val titles = parent.children map { case (t, u) => NavLink(t, u, t == ctx.title) }
        val url = parent.children.head._2
        getNav(parent, Section(parent.title, url, titles) :: acc)
    }
  }

  def renderDocVersion(version: Version): Seq[OutputFile] = {
    val docsPath = s"documentation/${version.name}/java"
    val versionOutputDir = new File(outputDir, docsPath)
    versionOutputDir.mkdirs()

    def processDocsFile(path: String, file: File): Seq[OutputFile] = {
      if (file.isDirectory) {
        new File(versionOutputDir, path).mkdirs()
        file.listFiles().flatMap { child =>
          val childPath = if (path.isEmpty) child.getName else path + "/" + child.getName
          processDocsFile(childPath, child)
        }
      } else {
        val targetFile = new File(versionOutputDir, path)
        val rendered = version.toc.mappings.get(path) match {
          case Some(context) if !context.nostyle =>
            val fileContent = Html(new String(Files.readAllBytes(file.toPath), "utf-8"))

            val versionPages = versions.map(_._2.pageFor(path))
            val nav = getNav(context)
            val canonical = currentVersion.map(_._2.pageFor(path)).collect {
              case VersionPage(name, true) => s"$baseUrl/documentation/$name/java/$path"
            }

            val rendered = html.documentation(path, fileContent, context, version.name, versionPages, nav, canonical)

            Files.write(targetFile.toPath, rendered.body.getBytes("utf-8"))
            OutputFile(targetFile, docsPath + "/" + path, includeInSitemap = true, "0.9")
          case _ =>
            // Simply copy the file as is
            if (targetFile.lastModified() < file.lastModified()) {
              Files.copy(file.toPath, targetFile.toPath, StandardCopyOption.REPLACE_EXISTING)
            }
            OutputFile(targetFile, docsPath + "/" + path, includeInSitemap = false, "")
        }
        Seq(rendered)
      }
    }

    processDocsFile("", new File(docsDir, version.name))
  }

  val generatedDocs = versions.map(_._2).map(version => version -> renderDocVersion(version))

  val generated = templatePages.map((generatePage _).tupled) ++ renderMarkdownFiles("", markdownDir) ++ blogPostFiles ++ redirects.map((generateRedirect _).tupled)

  // sitemaps
  val mainSitemap = Sitemap("sitemap-main.xml", generated.filter(_.includeInSitemap)
    .map(file => SitemapUrl(file.sitemapUrl, file.sitemapPriority)))

  val docsSitemap = Sitemap("sitemap-docs.xml", generatedDocs.find(_._1.name == currentDocsVersion).map {
    case (_, pages) =>
      pages.collect {
        case OutputFile(_, path, true, sitemapPriority) => SitemapUrl(path, sitemapPriority)
      }
  }.getOrElse(Nil))

  val generatedSitemaps = Sitemap.generateSitemaps(outputDir, baseUrl, Seq(mainSitemap, docsSitemap))

  val generatedSet: Set[File] = generated.map(_.file).toSet ++ generatedSitemaps ++ generatedDocs.flatMap(_._2.map(_.file))

  def cleanOldFiles(file: File): Unit = {
    if (file.isDirectory) {
      file.listFiles().foreach(cleanOldFiles)
      if (file.listFiles().isEmpty) {
        file.delete()
      }
    } else {
      if (!generatedSet.contains(file)) {
        file.delete()
      }
    }
  }

  cleanOldFiles(outputDir)

}

case class OutputFile(file: File, sitemapUrl: String, includeInSitemap: Boolean, sitemapPriority: String)

/**
  * The context that gets passed to every page in the documentation.
  *
  * @param currentLagomVersion The current version of Lagom.
  * @param currentDocsVersion The current version of the docs.
  * @param activatorRelease The current version of Activator.
  */
case class LagomContext(baseUrl: String, path: String, currentLagomVersion: String, currentDocsVersion: String,
                        activatorRelease: ActivatorRelease, blogSummary: BlogSummary)

case class ActivatorRelease(url: String, miniUrl: String, version: String, size: String, miniSize: String)

object ActivatorRelease {
  implicit val reads: Reads[ActivatorRelease] = Json.reads[ActivatorRelease]
}

/**
  * A version of the docs.
  *
  * @param name The name of the version.
  * @param toc The table of contents for the version.
  */
case class Version(name: String, toc: TOC) {
  def pageFor(path: String) = VersionPage(name, toc.mappings.get(path).isDefined)
}

/**
  * A link to another version from a specific page. Used when rendering the drop down to another version, if the page
  * exists in the other version we render a link to the page, otherwise, we render a link to the index.
  *
  * @param name The name of the version.
  * @param exists Whether the specific page exists in that version.
  */
case class VersionPage(name: String, exists: Boolean)

/**
  * A link to a page
  */
case class NavLink(title: String, url: String, current: Boolean)

/**
  * A documentation section
  */
case class Section(title: String, url: String, children: Seq[NavLink])

package object html {
  def encodePathSegment(url: String): String = {
    UriEncoding.encodePathSegment(url, "utf-8")
  }

}
