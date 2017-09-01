import org.apache.spark.streaming
import org.apache.spark.sql
import org.apache.spark.rdd

object ImporterApp {
  def main(args: Array[String]): Unit = {
    val ssc = setupStreamingContext("Archai Importer", "checkpoint/", buildImportPipeline)
    ssc.sparkContext.setLogLevel("ERROR")
    ssc.start()
    ssc.awaitTermination()
  }

  private def setupSparkSession(appName: String): sql.SparkSession = {
    sql.SparkSession.builder.appName(appName).getOrCreate
  }

  private def setupStreamingContext(appName: String, checkpointDir: String, setupStream: streaming.StreamingContext => streaming.StreamingContext): streaming.StreamingContext = {
    val createF = () => {
      val spark = setupSparkSession(appName)
      val ssc = new streaming.StreamingContext(spark.sparkContext, streaming.Seconds(5))
      ssc.checkpoint(checkpointDir)
      setupStream(ssc)
      // ssc.remember(streaming.Seconds(60))
      ssc
    }
    streaming.StreamingContext.getOrCreate(checkpointDir, createF)
  }

  private def buildImportPipeline(ssc: streaming.StreamingContext): streaming.StreamingContext = {
    val observations = ssc.socketTextStream("localhost", 10000).flatMap {
      // TODO: Duplication.
      // TODO: Report malformed input to DD.
      json => println(json); Seq(PostContentTag.extract(json)).flatten
    }.map { ct => (ct.postId, ct) }
    observations.foreachRDD(_.foreach(println(_)))
    ssc
  }
}
