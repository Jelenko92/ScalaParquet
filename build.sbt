name := "ScalaParquet"

version := "1.0"

scalaVersion := "2.10.6"

lazy val unprovidedDependencies = Seq(
  "org.apache.spark" %% "spark-core" % "1.6.2",
  "org.apache.spark" %% "spark-sql" % "1.6.2",
  "org.apache.kafka" % "kafka_2.10" % "0.10.2.1"
)

libraryDependencies ++= unprovidedDependencies

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
  case x => (assemblyMergeStrategy in assembly).value(x)
}