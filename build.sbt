name := """depth1grc"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.6",
  "com.datastax.cassandra" % "cassandra-driver-mapping" % "2.1.6",
  "com.codahale.metrics" % "metrics-core" % "3.0.2",
  "com.google.guava" % "guava" % "14.0.1",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.apache.shiro" % "shiro-core" % "1.2.3",
  "org.jsoup" % "jsoup" % "1.8.3",
  filters
  
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator