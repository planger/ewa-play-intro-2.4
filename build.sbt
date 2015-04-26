name := """ewa-play-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaCore,
  javaJpa,
  cache,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final"
)
