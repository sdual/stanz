import sbt._

object Dependencies {

  val scalaTest         = "org.scalatest"      %% "scalatest"    % "3.0.5"
  val scalaMock         = "org.scalamock"      %% "scalamock"    % "4.1.0"
  val apacheCommonsMath = "org.apache.commons" % "commons-math3" % "3.6.1"
  val cats              = "org.typelevel"      %% "cats-core"    % "2.0.0"
  val simulacrum        =  "org.typelevel"     %% "simulacrum"   % "1.0.0"

  val stanzCommonDependencies = Seq(
    apacheCommonsMath,
    scalaTest % Test,
    scalaMock % Test
  )
  
  val stanzCoreDependencies = Seq(
    cats,
    simulacrum
  ) ++ stanzCommonDependencies

}
