name := "main"

version := "1.0"

scalaVersion := "2.11.8"

version := "0.0.1"
scalaVersion := "2.11.7"
scalacOptions := Seq("-unchecked", "-deprecation", "-feature", "-encoding", "utf8", "-target:jvm-1.8", "-Xfatal-warnings", "-Xfuture")

libraryDependencies ++=
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.4.11",
    "com.typesafe.akka" %% "akka-http" % "3.0.0-RC1",
    "org.scalatest" %% "scalatest" % "3.0.0" % Test,
    "junit" % "junit" % "4.12" % Test,
    "org.scalamock" %% "scalamock-specs2-support" % "3.4.1" % Test
  )