name := """Ceiba Starter Template"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala, LauncherJarPlugin)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

libraryDependencies += guice
libraryDependencies += ehcache
libraryDependencies += ws

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.21.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-slick" % "3.0.3"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.16"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.47"
libraryDependencies += "com.h2database" % "h2" % "1.4.197"