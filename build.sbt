name := "Scala app"

version := "0.1.0"

scalaVersion := "2.11.0"

sbtVersion := "0.13.2"

organization := "mephi"

libraryDependencies ++= {
  Seq(
    "org.scala-lang" % "scala-compiler" % "2.11.0",
    "org.scalatest" % "scalatest_2.11" % "2.1.4",
    "org.specs2" %% "specs2" % "2.3.11" % "test",
    "org.scala-lang" % "scala-swing" % "2.11.0-M7",
    "io.argonaut" %% "argonaut" % "6.0.4",
    "io.spray" %%  "spray-json" % "1.2.6"
  )
}

mainClass := Some("mephi.oop.runner")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += "spray" at "http://repo.spray.io/"

scalacOptions ++= Seq("-unchecked", "-deprecation")

logLevel := Level.Info
