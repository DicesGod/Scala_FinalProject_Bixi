package ca.mcit.sprint2

import java.sql.{Connection, DriverManager, Statement}

object DatabaseDeployment{
  val driverName: String = "org.apache.hive.jdbc.HiveDriver"
  Class.forName(driverName)

  val connection: Connection = DriverManager.getConnection("jdbc:hive2://172.16.129.58:10000/fall2019_minhle;user=minhle;password=minhle")
  val stmt: Statement = connection.createStatement()
  stmt.execute("SET hive.exec.dynamic.partition.mode=nonstrict")

  def createDatabase: Unit = {
        stmt.execute("\nCREATE DATABASE IF NOT EXISTS s19909_bixi_feed_minhle")
  }

def createEnrichedTb: Unit = {
  //STATION_INFORMATION
    stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.enriched_sta_sys_info")
    println(" Dropped enriched_sta_sys_info table")
    stmt.execute("CREATE EXTERNAL TABLE s19909_bixi_feed_minhle.enriched_sta_sys_info (last_update DOUBLE," +
      "ttl INT," +
      "system_id STRING," +
      "language STRING," +
      "system_name STRING," +
      "system_short_name STRING," +
      "operator STRING," +
      "url STRING," +
      "purchase_url STRING," +
      "start_date STRING," +
      "phone_number STRING," +
      "email STRING," +
      "license_url STRING," +
      "timezone STRING," +
      //station_information fields
      "station_id STRING, " +
      "external_id STRING," +
      "station_name STRING," +
      "station_short_name STRING," +
      "lat DOUBLE," +
      "lon DOUBLE," +
      "rental_methods ARRAY<STRING>," +
      "capacity INT," +
      "electric_bike_surcharge_waiver Boolean," +
      "is_charging Boolean," +
      "eightd_has_key_dispenser Boolean," +
      "has_kiosk Boolean)" +
      "ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'" +
      "WITH SERDEPROPERTIES ('quoteChar' = '\"')    "+
      "LOCATION '/user/fall2019/minhle/final_project/feed_data/enriched_station_system_information'")
    println(" Created enriched_station_system_informmation table in s19909_bixi_feed_minhle database")
  }
}

