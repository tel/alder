logLevel := Level.Warn

resolvers += "spray repo" at "http://repo.spray.io"
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.9")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

addSbtPlugin("com.lihaoyi" % "workbench" % "0.2.3")
