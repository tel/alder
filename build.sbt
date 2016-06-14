
name := "Alder"
normalizedName := "alder"

val commonSettings = Seq(
  organization := "com.jspha",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-deprecation", "-feature", "-Xfatal-warnings")
)

commonSettings

homepage :=
  Some(url("http://github.com/tel/alder"))

licenses := Seq(
  "BSD3" -> url("https://opensource.org/licenses/BSD-3-Clause")
)

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.0"
)

scmInfo := Some(ScmInfo(
  url("https://github.com/tel/alder"),
  "scm:git:git@github.com:tel/alder.git",
  Some("scm:git:git@github.com:tel/alder.git")))

pomExtra in Global := {
  <developers>
    <developer>
      <id>tel</id>
      <name>Joseph Tel Abrahamson</name>
      <url>jspha.com</url>
    </developer>
  </developers>
}

lazy val root = project.in(file("."))
  .enablePlugins(ScalaJSPlugin)

val reactJsVersion = "15.0.2"

lazy val example = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    workbenchSettings,
    bootSnippet := "jspha.alder.example.AlderExample().main();",
    updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
  )
  .settings(
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  )
  .settings(
    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % reactJsVersion % "compile"
        / "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % reactJsVersion % "compile"
        / "react-dom.js"
        minified "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM"
    )
  )
  .dependsOn(root)
