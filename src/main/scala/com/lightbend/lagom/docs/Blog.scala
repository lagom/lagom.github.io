package com.lightbend.lagom.docs

import java.io.{File, FileInputStream}

import org.joda.time.DateTime

object Blog {

  /** Find the blog posts, sorted by date in reverse, ie most recent first */
  def findBlogPosts(blogDir: File): Seq[BlogPost] = {
    val blogPostFiles = blogDir.listFiles().toSeq.filter(file => file.getName.endsWith(".md") && !file.getName.startsWith("_"))

    val blogPosts = blogPostFiles.map { file =>
      val stream = new FileInputStream(file)
      try {
        BlogMetaDataParser.parsePostFrontMatter(stream, file.getName.dropRight(3))
      } finally {
        stream.close()
      }
    }

    blogPosts.sortBy(_.date.toDate.getTime).reverse
  }

}

final case class BlogPost(id: String, date: DateTime, markdown: String, title: String,
                          summary: String, author: BlogAuthor, tags: Set[String])

final case class BlogAuthor(name: String, url: String, avatar: String)

final case class BlogSummary(recent: Seq[BlogPost], tags: Seq[(String, Int)])