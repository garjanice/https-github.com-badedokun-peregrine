name := """depth1grc"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  javaWs,
  filters,
  "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.6",
  "com.datastax.cassandra" % "cassandra-driver-mapping" % "2.1.6",
  "com.codahale.metrics" % "metrics-core" % "3.0.2",
  "com.google.guava" % "guava" % "18.0",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.apache.shiro" % "shiro-core" % "1.2.3",
  "org.apache.pdfbox" % "pdfbox" % "1.8.10",
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.3.0",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.11.Final"
)


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
