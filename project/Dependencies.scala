import sbt._

object Dependencies {
  val finagleVersion = "18.7.0"
  val slf4jVersion = "1.7.21"
  val specs2Version = "3.6.3"

  lazy val finagleCore = "com.twitter" %% "finagle-core" % finagleVersion
  lazy val finagleHttp = "com.twitter" %% "finagle-http" % finagleVersion
  lazy val specs2 = "org.specs2" %% "specs2-core" % specs2Version % "test"
}
