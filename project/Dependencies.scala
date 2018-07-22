import sbt._

object Dependencies {

  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"

  val stanzDependencies = Seq(scalaTest % Test)

}
