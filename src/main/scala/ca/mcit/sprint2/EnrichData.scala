package ca.mcit.sprint2

import java.io.File
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

class EnrichData {
  /** on Local */
  def enrich_sta_sys_info: Any = {
    val station_informationFileName = "Feed/station_information.csv"
    val system_informationFileName = "Feed/system_information.csv"

    val spark: SparkSession = SparkSession.builder ()
    .appName ("Spark SQL practice").master ("local[*]")
    .getOrCreate ()

    val system_informationDf: DataFrame = spark.read.option ("header", "false").option ("inferschema", "true").csv (system_informationFileName)
    val station_informationDf: DataFrame = spark.read.option ("header", "false").option ("inferschema", "true").csv (station_informationFileName)

    system_informationDf.createOrReplaceTempView ("system_information")
    station_informationDf.createOrReplaceTempView ("station_information")

    val enriched_info = spark.sql (
    """SELECT sys._c0 system_id, sys._c1 language, sys._c2 name, sys._c3 short_name, sys._c4 operator, sys._c5 url, sys._c6 purchase_url, sys._c7 start_date, sys._c8 phone_number, sys._c9 email, sys._c10 license_url, sys._c11 timezone,
      |sta._c0, sta._c1, sta._c2, sta._c3, sta._c4, sta._c5, sta._c6, sta._c7, sta._c8, sta._c9, sta._c10, sta._c11
      |FROM system_information sys CROSS JOIN station_information sta
      |""".stripMargin)
    //.show()

    //Create enriched CSV file
    enriched_info.coalesce (1).write.mode (SaveMode.Overwrite).csv ("Feed/enriched_sta_sys_info/")

    val getListOfFiles = new GetListOfFiles

    new File (getListOfFiles.getListOfFiles (new File ("Feed/enriched_sta_sys_info/"), List ("csv") ).mkString)
    .renameTo (new File ("Feed/enriched_sta_sys_info/enriched_sys_sta_info.csv") )

    spark.stop ()
  }
}
