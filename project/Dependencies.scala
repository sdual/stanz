import sbt._

object Dependencies {

  val scalaTest         = "org.scalatest"      %% "scalatest"    % "3.0.5"
  val scalaMock         = "org.scalamock"      %% "scalamock"    % "4.1.0"
  val apacheCommonsMath = "org.apache.commons" % "commons-math3" % "3.6.1"

  val stanzDependencies = Seq(
    apacheCommonsMath,
    scalaTest % Test,
    scalaMock % Test
  )

}
