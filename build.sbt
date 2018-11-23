version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq (
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

lazy val sharedSettings = Seq(
	version       := "0.1",
	scalaVersion	:= "2.12.7"
)

val scalaTestVersion  = "3.0.5"
val akkaHttpVersion   = "10.1.5"
val akkaVersion       = "2.5.16"

lazy val RouteTestkit = (project in file ("."))
	.aggregate(web)

lazy val core = (project in file ("core")).
	settings(
		sharedSettings,
		libraryDependencies ++= Seq(
			"com.typesafe.akka"     %%      "akka-actor"            % akkaVersion,
			"com.typesafe.akka"     %%      "akka-stream"           % akkaVersion,
			"com.typesafe.akka"     %%      "akka-slf4j"            % akkaVersion,
			"com.typesafe.akka"     %%      "akka-http-spray-json"  % akkaHttpVersion,
			"org.scalatest"         %%      "scalatest"             % scalaTestVersion  % Test,
			"com.typesafe.akka"     %%       "akka-testkit"         % akkaVersion       % Test
	)
)

lazy val web = (project in file ("web")).
	settings(
		sharedSettings,
		libraryDependencies ++= Seq(
			"org.scalatest"     %%   "scalatest"          % scalaTestVersion  % Test,
			"com.typesafe.akka" %%   "akka-http-testkit"  % akkaHttpVersion   % Test,
			
	)
).dependsOn(core)
