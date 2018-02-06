
lazy val root = (project in file("."))
  .settings(
    name := "akka_test",
    scalaVersion := "2.12.1",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
      "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
      "org.iq80.leveldb" % "leveldb" % "0.10", //leveldb java版本依赖
      "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8", //leveldb java版本依赖
      "com.twitter" %% "chill-akka" % "0.9.2"
    ),
    libraryDependencies += "org.apache.kafka" % "kafka_2.12" % "0.10.1.1",
    // https://mvnrepository.com/artifact/org.scalatest/scalatest_2.12
    libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.5.9",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.6"
  )

lazy val akkaVersion = "2.5.2"

lazy val kafkaVersion = "0.10.1.1"





