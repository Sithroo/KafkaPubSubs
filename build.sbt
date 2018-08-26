import Dependencies._

lazy val commonSettings = Seq(
  description := "Kafka PubSubs",
  organization := "sithroo",
  version := "0.1",
  scalaVersion := "2.11.8",
  scalacOptions := Seq("-feature", "-deprecation")
)

lazy val mergeStrategy =
  assemblyMergeStrategy in assembly := {
    case x if x endsWith "BUILD" => MergeStrategy.first
    case x if x endsWith "io.netty.versions.properties" => MergeStrategy.first

    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }

lazy val assemblySettings = commonSettings ++ mergeStrategy ++ Seq(
  test in assembly := {}
)

lazy val root = Project(
  id = "kafka-pubsubs",
  base = file("."),
  settings = commonSettings
).aggregate(service, stream)

lazy val service = Project(
  id = "pubsubs-service",
  base = file("pubsubs-service"),
  settings = assemblySettings ++ Seq(
    libraryDependencies ++= Seq(
      finagleHttp,
      specs2
    )
  )
)

lazy val stream = Project(
  id = "pubsubs-stream",
  base = file("pubsubs-stream")
)