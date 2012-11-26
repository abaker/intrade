name := "intrade"

version := {
    val current = """\*\s+(\w+)""".r
    val branch = "git branch --no-color".lines_!.collect { case current(name) => "-" + name }
    "0.01" + branch.mkString
}

scalaVersion := "2.9.2"

resolvers += ScalaToolsReleases

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "junit" % "junit" % "4.9" % "test"
)
