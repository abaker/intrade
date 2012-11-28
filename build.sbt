name := "intrade"

version := "local"

scalaVersion := "2.9.2"

resolvers += ScalaToolsReleases

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "junit" % "junit" % "4.9" % "test"
)
