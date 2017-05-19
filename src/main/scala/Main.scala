import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Jelena on 19.5.2017..
  */
object Main {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("Some Application")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    //val parquetFile = sqlContext.read.parquet("/user/Jelena/dataSaved")
    val parquetFile = sqlContext.read.parquet("C:\\Users\\Jelena\\Documents\\dataSaved")
    parquetFile.registerTempTable("parquetFile")
    val zone = sqlContext.sql("SELECT COUNT(*) FROM parquetFile")
    //val zone = sqlContext.sql("SELECT DISTINCT(COMPUTED_ZONE_MASK) FROM parquetFile")
    zone.map(t => "COUNT: " + t(0)).collect().foreach(println)
    val numRows = zone.head(1)(0)
    val row = sqlContext.sql("SELECT TRANS_ID, OFFER_CODE_MASK from parquetFile where BROJ BETWEEN 1 and 6 ")
    row.map(t => "TRANS_ID: " + t(0) + "," + t(1)).collect().foreach(println)
  }
}
