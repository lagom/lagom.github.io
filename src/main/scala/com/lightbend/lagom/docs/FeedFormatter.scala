package com.lightbend.lagom.docs

import org.joda.time.DateTime

import scala.xml.{Elem, PCData}
import org.joda.time.format.ISODateTimeFormat
import play.twirl.api.Html

object FeedFormatter {
  def atom(posts: Seq[(BlogPost, Html)])(implicit context: LagomContext): Elem = {
    val blogUpdate = ISODateTimeFormat.dateTime.print(posts.head._1.date)
    val blogUrl = context.baseUrl + "/blog"
    val year = DateTime.now().getYear

    <feed xmlns="http://www.w3.org/2005/Atom" xmlns:dc="http://purl.org/dc/elements/1.1/">
      <title>Lagom Blog</title>
      <link rel="alternate" type="text/html" href={blogUrl} />
      <link rel="self" type="application/atom+xml" href={blogUrl + "/atom.xml"} />
      <id>{blogUrl}</id>
      <rights>Copyright {year}, Lightbend</rights>
      <updated>{blogUpdate}</updated>
      <dc:creator>Lightbend</dc:creator>
      <dc:date>{blogUpdate}</dc:date>
      <dc:language>en</dc:language>
      <dc:rights>Copyright {year}, Lightbend</dc:rights>
      {posts.map {
      case (post, content) =>
        val postDate = ISODateTimeFormat.dateTime.print(post.date)
        val postUrl = s"$blogUrl/${post.id}.html"

        <entry>
          <title>{post.title}</title>
          <link rel="alternate" href={postUrl} />
          {post.tags.map { tag =>
            <category term={tag} scheme={s"$blogUrl/tags/${html.encodePathSegment(tag)}.html"} />
        }}
          <author>
            <name>{post.author.name}</name>
            <uri>{post.author.url}</uri>
          </author>
          <id>{postUrl}</id>
          <updated>{postDate}</updated>
          <published>{postDate}</published>
          <content type="html">{PCData(content.body)}</content>
          <dc:date>{postDate}</dc:date>
        </entry>
    }}
    </feed>
  }
}
