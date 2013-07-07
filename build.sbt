organization := "me.lessis"

name := "pullboy"

version := "0.1.0-SNAPSHOT"

description := "github pull requests command line interface"

scalaVersion := "2.10.0"

resolvers += Classpaths.typesafeResolver

libraryDependencies += "me.lessis" %% "hubcat" % "0.1.1-SNAPSHOT"

libraryDependencies <+= (sbtVersion)(
  "org.scala-sbt" %
   "launcher-interface" %
    _ % "provided")

libraryDependencies += "com.github.scopt" %% "scopt" % "3.0.0"

