package com.lightbend.lagom.docs

import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset

import org.joda.time.DateTime

import scala.reflect.ClassTag
import play.api.Logger
import java.util.Date

import play.api.libs.json.{ Json, Reads }

import scala.collection.concurrent.TrieMap
import scala.util.control.NonFatal
import scala.io.{ Codec, Source }

object BlogMetaDataParser {

  private implicit class IteratorOps[T](i: Iterator[T]) {
    def nextOption = if (i.hasNext) Option(i.next()) else None
  }

  private implicit val codec = Codec(Charset.forName("UTF-8"))

  private val gitHubCache = TrieMap.empty[String, GitHubUser]

  /**
    * Parse the front matter from a blog post.
    *
    * @param stream The stream to parse
    * @param id The ID of the blog post
    */
  def parsePostFrontMatter(stream: InputStream, id: String): BlogPost = {
    val (yaml, markdown) = extractFrontMatter(stream)

    val title = yaml.getString("title").getOrElse {
      sys.error(s"No title specified in front matter of blog post $id")
    }
    val summary = yaml.getString("summary").getOrElse("")
    val date = yaml.getDate("date").getOrElse {
      sys.error(s"No date specified in front matter of blog post $id")
    }
    lazy val authorGithub = yaml.getString("author_github").map(getGitHubUser)
    val authorName = yaml.getString("author_name") orElse {
      authorGithub.map(_.name)
    } getOrElse {
      sys.error(s"No author_name or author_github in front matter of blog post $id")
    }
    val authorUrl = yaml.getString("author_url") orElse {
      authorGithub.map(_.html_url)
    } getOrElse {
      sys.error(s"No author_url or author_github in front matter of blog post $id")
    }
    val authorAvatar = yaml.getString("author_avatar") orElse {
      authorGithub.map(_.avatar_url)
    } getOrElse {
      sys.error(s"No author_avatar or author_github in front matter of blog post $id")
    }

    val canonicalRel = yaml.getString("canonical_rel")

    val tags = yaml.getString("tags").toSeq.flatMap(_.split(" +").map(_.replace('+', ' ')))

    BlogPost(id, date, markdown, title, summary, canonicalRel, BlogAuthor(authorName, authorUrl, authorAvatar), tags.toSet)
  }

  private def extractFrontMatter(stream: InputStream): (Yaml, String) = {
    val lines = Source.fromInputStream(stream).getLines()
      .dropWhile(_.isEmpty)

    // Extract attributes from the front matter
    lines.nextOption match {
      case Some(start) if start.trim == "---" =>
        val (frontMatterLines, contentLines) = lines.span(_.trim != "---")
        val frontMatter = frontMatterLines.mkString("\n")
        val content = contentLines.drop(1).mkString("\n")
        (Yaml.parse(frontMatter), content)
      case _ =>
        (Yaml.empty, lines.mkString("\n"))
    }
  }

  private def getGitHubUser(name: String): GitHubUser = {
    gitHubCache.getOrElseUpdate(name, {
      try {
        val stream = new URL(s"https://api.github.com/users/$name").openStream()
        try {
          Json.parse(stream).as[GitHubUser]
        } finally {
          stream.close()
        }
      } catch {
        case e: java.io.IOException if DocumentationGenerator.devMode => {
          // GitHub might be rate-limiting us. If we're in development mode, just ignore this.
          Logger.warn(s"GitHub might be rate-limiting us; using fallback for user $name")
          GitHubUser(name, "https://www.gravatar.com/avatar/default", s"https://github.com/$name")
        }
      }
    })
  }
}

case class GitHubUser(name: String, avatar_url: String, html_url: String)

object GitHubUser {
  implicit val reads: Reads[GitHubUser] = Json.reads[GitHubUser]
}

case class Yaml(map: Map[String, AnyRef]) {

  def getString(key: String) = getAs[String](key)
  def getInt(key: String) = getAs[Int](key)
  def getBoolean(key: String) = getAs[Boolean](key)
  def getDate(key: String) = getAs[DateTime](key)
  def getYamlMap(key: String) = getAs[Yaml](key)

  def getMap[T](key: String)(implicit ct: ClassTag[T]): Option[Map[String, T]] = getAs[Yaml](key).map(_.map.filter {
    case (k, t) if ct.runtimeClass.isInstance(t) => true
    case (k, other) =>
      Logger.warn("Ignoring map value for key " + k + ", expected " + ct + " but was " + other)
      false
  }.asInstanceOf[Map[String, T]])

  def getList[T](key: String)(implicit ct: ClassTag[T]): Option[List[T]] = getAs[List[_]](key).map(_.filter {
    case t if ct.runtimeClass.isInstance(t) => true
    case other =>
      Logger.warn("Ignoring list value for key " + key + ", expected " + ct + " but was " + other)
      false
  }.asInstanceOf[List[T]])

  def getAs[T](key: String)(implicit ct: ClassTag[T]): Option[T] = map.get(key).flatMap {
    case t if ct.runtimeClass.isInstance(t) => Some(t.asInstanceOf[T])
    case other =>
      Logger.warn("Ignoring value for key " + key + ", expected " + ct + " but was " + other)
      None
  }
}

object Yaml {
  val empty = Yaml(Map())

  def parse(yaml: String) = {

    import scala.collection.JavaConverters._

    def yamlToScala(obj: AnyRef): AnyRef = obj match {
      case map: java.util.Map[String, AnyRef] => new Yaml(map.asScala.toMap.mapValues(yamlToScala))
      case list: java.util.List[AnyRef] => list.asScala.toList.map(yamlToScala)
      case s: String => s
      case n: Number => n
      case b: java.lang.Boolean => b
      case d: Date => new DateTime(d)
      case null => null
      case other =>
        Logger.warn("Unexpected YAML object of type " + other.getClass)
        other.toString
    }

    try {
      yamlToScala(new org.yaml.snakeyaml.Yaml().load(yaml)) match {
        case y: Yaml => y
        case other =>
          Logger.warn("YAML was not object: " + other)
          Yaml.empty
      }
    } catch {
      case NonFatal(t) =>
        Logger.warn("Error parsing YAML content", t)
        Yaml.empty
    }
  }
}
