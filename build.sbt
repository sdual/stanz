import Dependencies._

import scala.scalanative.sbtplugin.ScalaNativePlugin


lazy val commonSettings = Seq(
  organization        := "com.github.sdual",
  version             := "0.1.0-SNAPSHOT",
  scalaVersion        := "2.12.8",
)

lazy val root = (project in file("."))
  .aggregate(
    core, 
    example
  ).enablePlugins(ScalaNativePlugin)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name                := "stanz",
    libraryDependencies ++= stanzDependencies,
  )

lazy val example = (project in file("example"))
  .settings(
    commonSettings,
    name                := "example",
    libraryDependencies ++= stanzDependencies,
  ).dependsOn(core)
