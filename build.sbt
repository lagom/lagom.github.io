import scala.sys.process._
import java.io.Closeable
import sbt.PathFinder

import scala.concurrent.Await

lazy val `lagom-docs` = (project in file("."))
  .enablePlugins(SbtTwirl, SbtWeb)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.webjars" % "normalize.css" % "3.0.2",
  "org.webjars" % "foundation" % "6.2.0",
  "org.webjars" % "jquery" % "2.2.1",
  "org.webjars.bower" % "waypoints" % "4.0.0",
  "org.webjars" % "prettify" % "4-Mar-2013",
  "com.lightbend.markdown" %% "lightbend-markdown-server" % "1.5.0",
  "org.yaml" % "snakeyaml" % "1.12"
)

resolvers += Resolver.bintrayIvyRepo("typesafe", "ivy-releases")

lazy val assetFingerPrint = "git rev-parse HEAD".!!.trim

val httpServer = AttributeKey[Closeable]("http-server")

val stopCommand = Command.command("stop") { state =>
  state.attributes.get(httpServer) match {
    case Some(server) =>
      server.close()
      state.remove(httpServer)
    case None => state
  }
}

val runCommand = Command.make("run") { state =>
  import complete.Parsers._
  import complete.Parser
  import scala.concurrent.duration._

  (Space ~> NatBasic).?.map { maybePort =>
    () =>
      val port = maybePort.getOrElse(8080)

      val log = state.log
      val extracted = Project.extract(state)

      val stageDir = extracted.get(WebKeys.stagingDirectory)

      log.info(s"\u001b[32mRunning HTTP server on http://localhost:$port, press ENTER to exit...\u001b[0m")
      val simpleHttpServer = SimpleHTTPServer(stageDir, port)
      Await.ready(simpleHttpServer.bindingFuture, 5.seconds)

      val stateWithStop = "stop" :: state.put(httpServer, new Closeable {
        override def close(): Unit = {
          log.info("Shutting down HTTP server")
          simpleHttpServer.close()
        }
      }).addExitHook(() => simpleHttpServer.close())

      val extraSettings = Seq(
        javaOptions += "-Ddev",
        fork := true // required for javaOptions to take effect
      )
      val stateWithExtraSettings = extracted.appendWithSession(extraSettings, stateWithStop)
      Parser.parse("~webStage", stateWithExtraSettings.combinedParser) match {
        case Right(cmd) => cmd()
        case Left(msg) => throw sys.error(s"Invalid command:\n$msg")
      }
  }
}

commands ++= Seq(runCommand, stopCommand)

val generateHtml = taskKey[Seq[File]]("Generate the site HTML")

target in generateHtml := WebKeys.webTarget.value / "generated-html"
generateHtml := Def.taskDyn {
  val outputDir = (target in generateHtml).value
  val docsDir = sourceDirectory.value / "docs"
  val markdownDir = (sourceDirectory in Compile).value / "markdown"
  val blogDir = sourceDirectory.value / "blog"
  Def.task {
    (runMain in Compile).toTask(Seq(
      "com.lightbend.lagom.docs.DocumentationGenerator",
      outputDir,
      docsDir,
      markdownDir,
      blogDir,
      assetFingerPrint
    ).mkString(" ", " ", "")).value
    outputDir.allPaths.filter(_.isFile).get
  }
}.value

def path(segments: String*): String =  segments.mkString(java.io.File.separator)

Concat.groups := Seq(
  s"$assetFingerPrint-all-styles-concat.css" -> group(Seq(
      path("lib", "foundation", "dist", "foundation.min.css"),
      path("lib", "prettify", "prettify.css"),
      "main.min.css"
  )),
  s"$assetFingerPrint-all-scripts-concat.js" -> group(Seq(
    path("lib", "jquery", "jquery.min.js"),
    path("lib", "foundation", "dist", "foundation.min.js"),
    path("lib", "waypoints", "lib", "jquery.waypoints.min.js"),
    path("lib", "waypoints", "lib", "shortcuts", "sticky.min.js"),
    path("lib", "prettify", "prettify.js"),
    path("lib", "prettify", "lang-scala.js"),
    "main.min.js"
  ))
)

StylusKeys.compress := true

pipelineStages := Seq(uglify, concat)
WebKeys.pipeline ++= {
  generateHtml.value pair Path.relativeTo((target in generateHtml).value)
}
watchSources ++= {
  val markdownFolder: File = (sourceDirectory in Compile).value / "markdown"
  val blogFolder: File = sourceDirectory.value / "blog"
  val markdown: Seq[File] = markdownFolder.allPaths.get
  val blog: Seq[File] = blogFolder.allPaths.get
  markdown ++ blog
}

// Include hidden files in the output (e.g., src/main/public/.well-known)
excludeFilter in Assets := NothingFilter
