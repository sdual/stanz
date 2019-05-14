import Dependencies._

lazy val stanzCore = (project in file("core"))
  .settings(
    name                := "stanz",
    organization        := "com.github.sdual",
    scalaVersion        := "2.12.8",
    version             := "0.1.0-SNAPSHOT",
    libraryDependencies ++= stanzDependencies,
  )

lazy val stanzExample = (project in file("example"))
  .settings(
      name                := "example",
      organization        := "com.github.sdual",
      scalaVersion        := "2.12.8",
      version             := "0.1.0-SNAPSHOT",
      libraryDependencies ++= stanzDependencies, 
  )
