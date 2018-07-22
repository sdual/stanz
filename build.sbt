import Dependencies._

lazy val stanz = (project in file("."))
  .settings(
    name                := "stanz",
    organization        := "com.github.stenoritama.stanz",
    scalaVersion        := "2.12.6",
    version             := "0.1.0-SNAPSHOT",
    libraryDependencies ++= stanzDependencies,
  )
