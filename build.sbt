import java.io.Closeable

lazy val `lagom-docs` = (project in file("."))
  .enablePlugins(SbtTwirl, SbtWeb)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.webjars" % "normalize.css" % "3.0.2",
  "org.webjars" % "foundation" % "6.2.0",
  "org.webjars" % "jquery" % "2.2.1",
  "org.webjars.bower" % "waypoints" % "4.0.0",
  "org.webjars" % "prettify" % "4-Mar-2013",
  "com.lightbend.markdown" %% "lightbend-markdown-server" % "1.3.2",
  "org.yaml" % "snakeyaml" % "1.12"
)

resolvers += Resolver.bintrayIvyRepo("typesafe", "ivy-releases")

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

  (Space ~> NatBasic).?.map { maybePort =>
    () =>
      val port = maybePort.getOrElse(8000)

      val log = state.log
      val extracted = Project.extract(state)

      val stageDir = extracted.get(WebKeys.stagingDirectory)

      log.info(s"\u001b[32mRunning HTTP server on port $port, press ENTER to exit...\u001b[0m")
      val httpServerProcess = Process(s"python -m SimpleHTTPServer $port", stageDir).run(new ProcessLogger {
        override def info(s: => String): Unit = log.info(s)
        override def error(s: => String): Unit = log.info(s)
        override def buffer[T](f: => T): T = f
      })

      val stateWithStop = "stop" :: state.put(httpServer, new Closeable {
        override def close(): Unit = {
          log.info("Shutting down HTTP server")
          httpServerProcess.destroy()
        }
      }).addExitHook(() => httpServerProcess.destroy())

      val extraSettings = Seq(
        javaOptions += "-Ddev",
        fork := true // required for javaOptions to take effect
      )
      val stateWithExtraSettings = extracted.append(extraSettings, stateWithStop)
      Parser.parse("~web-stage", stateWithExtraSettings.combinedParser) match {
        case Right(cmd) => cmd()
        case Left(msg) => throw sys.error(s"Invalid command:\n$msg")
      }
  }
}

commands ++= Seq(runCommand, stopCommand)

val generateHtml = taskKey[Seq[File]]("Generate the site HTML")

target in generateHtml := WebKeys.webTarget.value / "generated-html"
generateHtml <<= Def.taskDyn {
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
      blogDir
    ).mkString(" ", " ", "")).value
    outputDir.***.filter(_.isFile).get
  }
}

Concat.groups := Seq(
  "all-styles-concat.css" -> group(Seq(
      "lib/foundation/dist/foundation.min.css",
      "lib/prettify/prettify.css",
      "main.min.css"
  )),
  "all-scripts-concat.js" -> group(Seq(
    "lib/jquery/jquery.min.js",
    "lib/foundation/dist/foundation.min.js",
    "lib/waypoints/lib/jquery.waypoints.min.js",
    "lib/waypoints/lib/shortcuts/sticky.min.js",
    "lib/prettify/prettify.js",
    "lib/prettify/lang-scala.js",
    "main.min.js"
  ))
)

StylusKeys.compress := true

pipelineStages := Seq(uglify, concat)
WebKeys.pipeline ++= {
  generateHtml.value pair relativeTo((target in generateHtml).value)
}
watchSources ++= {
  ((sourceDirectory in Compile).value / "markdown").***.get ++
    (sourceDirectory.value / "blog").***.get
}
