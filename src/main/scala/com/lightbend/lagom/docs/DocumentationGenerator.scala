package com.lightbend.lagom.docs

import java.io.File
import java.nio.file.{Files, StandardCopyOption}

import com.lightbend.docs.{Context, TOC}
import org.pegdown.{Extensions, LinkRenderer, PegDownProcessor, VerbatimSerializer}
import play.api.Logger
import play.api.libs.json.Json
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
  val currentDocsVersion = "1.4.x"
  val currentLagomVersion = "1.4.8"

  // This impacts what gets displayed on the main documentation index.
  val stableVersions = Seq(
    VersionSummary("1.4.x", s"Lagom $currentLagomVersion (current stable release)"),
    VersionSummary("1.3.x", s"Lagom 1.3.11 (previous stable release)")
  )

  val previewVersions = Seq(
    VersionSummary("master", s"Lagom 1.5.0-M3 (preview)")
  )

  val oldVersions = Seq(
    VersionSummary("1.2.x", s"Lagom 1.2.3"),
    VersionSummary("1.1.x", s"Lagom 1.1.0"),
    VersionSummary("1.0.x", s"Lagom 1.0.0")
  )

  val communityContents = Seq(
  //    CommunityContent("title", "href", "hrefTitle"),

    CommunityContent("Lagom 1.4 and Kubernetes Orchestration",
      "https://ordina-jworks.github.io/orchestration/2018/05/22/lagom-1-4-and-kubernetes-orchestration.html",
      "JWORKS TECH BLOG"),
    CommunityContent("Event Sourcing with Lagom on GCP",
      "https://medium.com/google-cloud/event-sourcing-with-lagom-on-gcp-95f94a282fbe",
      "Ido Shamun"),
    CommunityContent("Lagom tutorial: using Event Sourcing to create an online shopping cart",
      "https://inviqa.com/blog/lagom-tutorial-using-event-sourcing-create-online-shopping-cart",
      "Inviqa"),
    CommunityContent("Lagom Circuit Breaker: What, Why and How?",
      "https://blog.knoldus.com/2017/06/15/lagom-circuit-breaker-what-why-and-how/",
      "Knoldus"),
    CommunityContent("Tour de Lagom (blog post series)",
      "https://manuel.bernhardt.io/2017/06/05/tour-de-lagom-part-1-lagom-microservices/",
      "Manuel Bernhardt"),
    CommunityContent("Guide to Reactive Microservices Using Lagom Framework",
      "http://www.baeldung.com/lagom-reactive-microservices",
      "Baeldung"),
    CommunityContent("CQRS and Event Sourcing with Lagom",
      "https://blog.codecentric.de/en/2017/02/cqrs-event-sourcing-lagom/",
      "codecentric Blog"),
    CommunityContent("Lagom 1.2: What's New?",
      "https://ordina-jworks.github.io/microservices/2017/02/01/Lagom-1-2.html",
      "JWORKS TECH BLOG"),
    CommunityContent("Run a Lagom service standalone with Zookeeper",
      "https://thecoderwriter.wordpress.com/2016/09/24/run-a-lagom-service-standalone-with-zookeeper/",
      "Coder's IO"),
    CommunityContent("The Lagom Framework for Microservices and Domain-Driven Design",
      "https://jaxenter.com/the-lagom-framework-tutorial-130264.html",
      "jaxenter")
  )

  val videos = Seq(
//    VideoLink(description, href),
    VideoLink("Begin Event Sourcing with Lagom", "https://youtube.com/watch?v=Sx8ctqLxDf0"),
    VideoLink("EventSourcing and CQRS with Lagom Microservice Framework", "https://youtube.com/watch?v=BuyXxw79fKk"),
    VideoLink("CQRS and Event Sourcing with Lagom by Miel Donkers", "https://youtube.com/watch?v=1VOfcuWOuy8"),
    VideoLink("Event Sourcing and CQRS by Lutz Huehnken", "https://youtube.com/watch?v=Z6_Nd7lu2PI"),
    VideoLink("Events-First Microservices with Lagom by Gideon de Kok", "https://youtube.com/watch?v=f1YSSaI_J-c"),
    VideoLink("Lagom in Practice by Yannick De Turck", "https://youtube.com/watch?v=JOGlZzY6ycI")
  )



  // Set this to Some("your-github-account-name") if you want to deploy the docs to the gh-pages of your own fork
  // of the repo
  val gitHubAccount: Option[String] = None

  val (baseUrl, context) = gitHubAccount match {
    case Some(account) => (s"http://$account.github.io/lagom.github.io", "/lagom.github.io")
    case None => ("https://www.lagomframework.com", "")
  }

  // Templated pages to generate
  val templatePages: Seq[(String, Template1[LagomContext, Html])] = Seq(
    "index.html" -> html.index,
    "get-involved.html" -> html.getinvolved,
    "get-started.html" -> html.getstarted,
    "get-started-java.html" -> html.getstartedjava,
    "get-started-java-sbt.html" -> html.getstartedjavasbt,
    "get-started-java-maven.html" -> html.getstartedjavamaven,
    "get-started-scala.html" -> html.getstartedscala,
    "support.html" -> html.support,
    "cookie.html" -> html.cookiePolicy
  )

  // Redirects
  // Since this is a static site, and GitHub doesn't support redirects, we generate pages that use HTML redirects.
  val redirects: Seq[(String, String)] = Seq(
    "/documentation/scala/index.html" -> s"$context/documentation/$currentDocsVersion/scala/Home.html",
    "/documentation/java/index.html" -> s"$context/documentation/$currentDocsVersion/java/Home.html",
    // Redirect anyone heading to the old download page to the get started page
    "/download.html" -> "/get-started.html"
  )

  val outputDir = new File(args(0))
  val docsDir = new File(args(1))
  val markdownDir = new File(args(2))
  val blogDir = new File(args(3))
  val assetFingerPrint = args(4)

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
    blogSummary, assetFingerPrint)

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
        val fixedLinks = if (context.nonEmpty) FeedFormatter.makeAbsoluteLinks(renderedPost, context) else renderedPost
        val page = html.blogPost(post, fixedLinks)
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
  val versions = docsDir.listFiles().toSeq.map { versionDir =>
    // Discover languages
    val languages = versionDir.listFiles().toSeq.map { languageDir =>
      val indexJson = new File(languageDir, "index.json")
      val toc = Json.parse(Files.readAllBytes(indexJson.toPath)).as[TOC]
      LanguageVersion(versionDir.getName, languageDir.getName, languageDir, toc)
    }.sortWith( _.language < _.language)  // enforce consistent ordering of languages
    //sortWith( _.language < _.language)  will list 'Java' before 'Scala'. Change
    // sorting function or reimplement for more elaborate scenarios.

    Version(versionDir.getName, languages)
  }.sortBy(_.name) // Will need a better sort in future


  val currentVersion = versions.find(_.name == currentDocsVersion)

  private def getNav(ctx: Context, acc: List[Section] = Nil): List[Section] = {
    ctx.parent match {
      case None => acc
      case Some(parent) =>
        val titles = parent.children map { case (t, u) => NavLink(t, u, t == ctx.title) }
        val url = parent.children.head._2
        getNav(parent, Section(parent.title, url, titles) :: acc)
    }
  }

  def renderDocVersion(version: LanguageVersion): Seq[OutputFile] = {
    val docsPath = s"documentation/${version.name}/${version.language}"
    val versionOutputDir = new File(outputDir, docsPath)
    versionOutputDir.mkdirs()

    val languageVersions = versions.collect(Function.unlift((v: Version) => v.versionFor(version.language)))
    val currentLanguageVersion = currentVersion.flatMap(_.versionFor(version.language))

    def getNext(ctx: Context): Seq[NavLink] = {
      ctx.nextUrls match {
        case None =>
          ctx.parent match {
            case None => Nil
            case Some(parent) =>
              parent.children.dropWhile(_._1 != ctx.title).drop(1).headOption match {
                case Some((title, url)) => Seq(NavLink(title, url, false))
                case None => getNext(parent)
              }
          }
        case Some(urls) =>
          urls.flatMap { url =>
            version.toc.mappings.get(url).map(ctx => NavLink(ctx.title, url, false)).toSeq
          }
      }
    }

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

            val versionPages = languageVersions.map(_.pageFor(path))
            val nav = getNav(context)
            val canonical = currentLanguageVersion.map(_.pageFor(path)).collect {
              case VersionPage(name, true, _) => s"$baseUrl/documentation/$name/${version.language}/$path"
            }

            val nextLinks = getNext(context)

            val rendered = html.documentation(path, fileContent, context, version, versionPages, nav, canonical, nextLinks)

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

    processDocsFile("", version.file)
  }

  val generatedDocs = versions.flatMap(_.languages).map { version =>
    version -> renderDocVersion(version)
  }

  val generated = templatePages.map((generatePage _).tupled) ++
    Seq(savePage("documentation/index.html", html.documentationIndex(stableVersions, previewVersions, oldVersions, versions, communityContents, videos))) ++
    versions.map { version =>
      savePage(s"documentation/${version.name}/index.html", html.documentationVersionIndex(version), includeInSitemap = false)
    } ++
    renderMarkdownFiles("", markdownDir) ++
    blogPostFiles ++
    redirects.map((generateRedirect _).tupled)

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
  */
case class LagomContext(baseUrl: String, path: String, currentLagomVersion: String, currentDocsVersion: String,
                        blogSummary: BlogSummary, assetFingerPrint: String)

case class VersionSummary(name: String, title: String)

case class CommunityContent(description:String, href:String, hrefTitle:String)

case class VideoLink(description:String, href:String)

case class Version(name: String, languages: Seq[LanguageVersion]) {
  def versionFor(language: String): Option[LanguageVersion] = languages.find(_.language == language)
}

/**
  * A version of the docs for a language.
  *
  * @param name The name of the version.
  * @param language The name of the language.
  * @param file The path of the language version.
  * @param toc The table of contents for the version.
  */
case class LanguageVersion(name: String, language: String, file: File, toc: TOC) {
  def pageFor(path: String) = VersionPage(name, toc.mappings.get(path).isDefined, path)

  private val apiRoot = (name, language) match {
    case (_, "java") => "api/index.html"
    // Note that this version check will break and need to be updated if we ever have a 1.10+
    case (version, "scala") if version >= "1.4" => "api/com/lightbend/lagom/scaladsl/index.html"
    case (version, "scala") if version < "1.4" => "api/index.html#com.lightbend.lagom.scaladsl.package"
  }

  def referenceManualPath(implicit ctx: LagomContext) = s"${ctx.path}/documentation/$name/$language/Home.html"
  def apiDocPath(implicit ctx: LagomContext) = s"${ctx.path}/documentation/$name/$language/$apiRoot"
}

/**
  * A link to another version from a specific page. Used when rendering the drop down to another version, if the page
  * exists in the other version we render a link to the page, otherwise, we render a link to the index.
  *
  * @param name The name of the version.
  * @param exists Whether the specific page exists in this version.
  * @param path The path to the specific page, which may or may not exist in this version.
  */
case class VersionPage(name: String, exists: Boolean, path: String) {
    def existingPath: Option[String] = if (exists) Some(path) else None

    def existingPathOrDefault: String = existingPath.getOrElse("Home.html")
}

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
