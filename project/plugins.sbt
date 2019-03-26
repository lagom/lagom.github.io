addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.4.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-web" % "1.4.4")
addSbtPlugin("com.typesafe.sbt" % "sbt-js-engine" % "1.2.3")
addSbtPlugin("com.typesafe.sbt" % "sbt-stylus" % "1.1.0")
addSbtPlugin("net.ground5hark.sbt" % "sbt-concat" % "0.2.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-uglify" % "2.0.0")

resolvers += "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/"

// Used for our SimpleHTTPServer in Akka HTTP
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.8"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.21"
