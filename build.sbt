name := "RouteTestkit"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)