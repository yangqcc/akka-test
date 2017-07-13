lazy val root = (project in file("."))

  .settings(
    name := "scala_test",

    //指定sbt版本
    version := "1.0",

    scalaVersion := "2.12.1"
  )

lazy val akkaVersion = "2.5.2"

lazy val kafkaVersion = "0.10.1.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

libraryDependencies += "org.apache.kafka" % "kafka_2.12" % "0.10.1.1"