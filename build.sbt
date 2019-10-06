import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "bot-review",
    libraryDependencies ++= Seq(scalaTest % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.1" % "test",
      "org.typelevel" %% "cats-effect" % "2.0.0",
      "org.typelevel" %% "cats-core" % "2.0.0")
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
