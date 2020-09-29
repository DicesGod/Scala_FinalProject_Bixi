package ca.mcit.sprint2

import java.sql.{Connection, DriverManager, Statement}

class DatabaseDeployment{
  val driverName: String = "org.apache.hive.jdbc.HiveDriver"
  Class.forName(driverName)

  val connection: Connection = DriverManager.getConnection("jdbc:hive2://172.16.129.58:10000/fall2019_minhle;user=minhle;password=minhle")
  val stmt: Statement = connection.createStatement()
  stmt.execute("SET hive.exec.dynamic.partition.mode=nonstrict")

  def createDatabase: Any = {
    var checkDB = true
    val rs = stmt.executeQuery("SHOW DATABASES")
    while ( {
      rs.next()
    }) if (rs.getString(1).contains("s19909_bixi_feed_minhle")) checkDB = true
    else {checkDB = false}
    try {
      if (!checkDB) {
        stmt.execute("\nCREATE DATABASE s19909_bixi_feed_minhle")
        println("Database deployment:")
        println(" Database s19909_bixi_feed_minhle is created")
      }
    }
    catch {
      case _: Exception => println("Database deployment:\n" +
        " Database s19909_bixi_feed_minhle already exists")
    }
  }

def createEnrichedTb: Any = {
  //STATION_INFORMATION
  stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.enriched_sta_sys_info")
  println(" Dropped enriched_sta_sys_info table")
  stmt.execute("CREATE TABLE s19909_bixi_feed_minhle.enriched_sta_sys_info (system_id STRING," +
    "language STRING," +
    "system_name STRING," +
    "system_short_name STRING," +
    "operator STRING," +
    "url STRING," +
    "purchase_url STRING," +
    "start_date DATE," +
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
    "rental_methods Array<STRING>," +
    "capacity INT," +
    "electric_bike_surcharge_waiver Boolean," +
    "is_charging Boolean," +
    "eightd_has_key_dispenser Boolean," +
    "has_kiosk Boolean)" +
    "ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE " +
    "LOCATION '/user/fall2019/minhle/final_project/feed_data/enriched_station_system_information'")
  println(" Created enriched_station_system_informmation table in s19909_bixi_feed_minhle database")
  }
}

