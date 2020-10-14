package ca.mcit.sprint2

import java.io.File
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object EnrichData{
  /** on Local */
  def enrichStationsSystemInfo: Unit = {
    val stationInformationFileName = "Feed/station_information.csv"
    val systemInformationFileName = "Feed/system_information.csv"

    val spark: SparkSession = SparkSession.builder ()
    .appName ("Spark SQL practice").master ("local[*]")
    .getOrCreate ()

    val system_informationDf: DataFrame = spark.read.option ("header", "true").option ("inferschema", "true")
      .csv (systemInformationFileName)
    val station_informationDf: DataFrame = spark.read.option ("header", "true").option ("inferschema", "true")
      .csv (stationInformationFileName)

    system_informationDf.createOrReplaceTempView ("system_information")
    station_informationDf.createOrReplaceTempView ("station_information")

        val enriched_info = spark.sql (
          """SELECT sys.last_updated, sys.ttl, `data.system_id`, `data.language`,`data.name`,
            |`data.short_name`,`data.operator`,`data.url`,`data.purchase_url`,`data.start_date`,
            |`data.phone_number`,`data.email`, `data.license_url`, `data.timezone`,
            |`data.stations.station_id`, `data.stations.external_id`,
            |`data.stations.name`, `data.stations.short_name`, `data.stations.lat`, `data.stations.lon`,
            |`data.stations.rental_methods`, `data.stations.capacity`, `data.stations.electric_bike_surcharge_waiver`,
            |`data.stations.is_charging`, `data.stations.eightd_has_key_dispenser`, `data.stations.has_kiosk`
            |FROM system_information sys CROSS JOIN station_information sta
            |""".stripMargin)

    //Create enriched CSV file
    enriched_info.coalesce (1).write.mode (SaveMode.Overwrite).csv ("Feed/enriched_sta_sys_info/")

    val getListOfFiles = new GetListOfFiles

    new File (getListOfFiles.getListOfFiles (new File ("Feed/enriched_sta_sys_info/"), List ("csv") ).mkString)
    .renameTo (new File ("Feed/enriched_sta_sys_info/enriched_sys_sta_info.csv") )

    spark.stop ()
  }
}
