import Dependencies._

scalacOptions ++= Seq("-Ypartial-unification", "-Ymacro-annotations")

lazy val commonSettings = Seq(
  organization := "com.github.sdual",
  version      := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.8",
)

lazy val root = (project in file("."))
  .aggregate(
    prototype,
    example
  )

lazy val core = (project in file("core"))
  .settings(
    commonSettings,
    name                := "stanz",
    libraryDependencies ++= stanzCoreDependencies,
  )

lazy val prototype = (project in file("prototype"))
  .settings(
    commonSettings,
    name                := "prototype",
    libraryDependencies ++= stanzCommonDependencies,
  )

lazy val example = (project in file("example"))
  .settings(
    commonSettings,
    name                := "example",
    libraryDependencies ++= stanzCommonDependencies,
  ).dependsOn(prototype)
