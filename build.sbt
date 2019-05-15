import Dependencies._

lazy val commonSettings = Seq(
  organization        := "com.github.sdual",
  version             := "0.1.0-SNAPSHOT",
  scalaVersion        := "2.12.8",
)

lazy val stanzCore = (project in file("core"))
  .settings(
    commonSettings,
    name                := "stanz",
    libraryDependencies ++= stanzDependencies,
  )

lazy val stanzExample = (project in file("example"))
  .settings(
    commonSettings,
    name                := "example",
    libraryDependencies ++= stanzDependencies,
  ).dependsOn(stanzCore)
