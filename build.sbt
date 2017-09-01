name := "ArchaiImporter"
version := "0.0.1"
organization := "com.g9"
scalaVersion := "2.11.8"
val sparkVersion = "2.2.0"
libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % sparkVersion % "provided",
  "org.apache.spark" % "spark-streaming_2.11" % sparkVersion % "provided",
  "org.apache.spark" % "spark-sql_2.11" % sparkVersion % "provided",
  // "org.apache.spark" % "spark-mllib_2.11" % sparkVersion % "provided",
  "org.json4s" % "json4s-native_2.11" % "3.5.3"//,
  // "org.json4s" % "json4s-ext_2.11" % "3.5.3"//,
  // "org.apache.bahir" %% "spark-streaming-pubsub" % "2.1.1",
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "2.2.0_0.7.2" % "test"

dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.6.5"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.5"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.6.5"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)
resolvers += Resolver.mavenLocal

// https://stackoverflow.com/questions/25144484/sbt-assembly-deduplication-found-error
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
// assemblyMergeStrategy in assembly := {
//   case PathList("org","aopalliance", xs @ _*) => MergeStrategy.last
//   case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
//   case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
//   case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
//   case PathList("org", "apache", xs @ _*) => MergeStrategy.last
//   case PathList("com", "google", xs @ _*) => MergeStrategy.last
//   case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
//   case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
//   case PathList("com", "yammer", xs @ _*) => MergeStrategy.las
//   case "about.html" => MergeStrategy.rename
//   case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
//   case "META-INF/mailcap" => MergeStrategy.last
//   case "META-INF/mimetypes.default" => MergeStrategy.last
//   case "plugin.properties" => MergeStrategy.last
//   case "log4j.properties" => MergeStrategy.last
//   case x =>
//     val oldStrategy = (assemblyMergeStrategy in assembly).value
//     oldStrategy(x)
// }
