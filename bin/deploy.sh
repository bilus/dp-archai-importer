sbt assembly && spark-submit --conf spark.driver.host=localhost --class "$1" target/scala-2.11/ArchaiImporter-assembly-0.0.1.jar
