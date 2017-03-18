name := """play_examples"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

val cassandraVersion = "3.1.3"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  evolutions,
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.0-M3",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.datastax.cassandra" % "cassandra-driver-core" % cassandraVersion,
  "com.datastax.cassandra" % "cassandra-driver-mapping" % cassandraVersion,
  "com.datastax.cassandra" % "cassandra-driver-extras" % cassandraVersion,
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.0-akka-2.4.x",
  "org.quartz-scheduler" % "quartz" % "2.2.3",
  "log4j" % "log4j" % "1.2.17",
  "org.postgresql" % "postgresql" % "9.4.1207.jre7",
  "org.scalikejdbc"  %% "scalikejdbc"   % "2.3.5",
  "org.scalikejdbc"  %% "scalikejdbc-config" % "2.3.5",
  "ch.qos.logback"   % "logback-classic"  % "1.1.3"

)

