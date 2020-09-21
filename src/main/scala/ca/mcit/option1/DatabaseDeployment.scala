package ca.mcit.option1

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
      }
    }
    catch {
      case _: Exception => println("\nDatabase s19909_bixi_feed_minhle already exists")
    }
  }

def createDBTables: Any = {
  //STATION_INFORMATION
  stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.station_information")
  stmt.execute("CREATE TABLE s19909_bixi_feed_minhle.station_information (station_id STRING, " +
    "external_id STRING," +
    "name STRING," +
    "short_name STRING," +
    "lat DOUBLE," +
    "lon DOUBLE," +
    "rental_methods Array<STRING>," +
    "capacity INT," +
    "electric_bike_surcharge_waiver Boolean," +
    "is_charging Boolean," +
    "eightd_has_key_dispenser Boolean," +
    "has_kiosk Boolean)" +
    "ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE " +
    "LOCATION '/user/fall2019/minhle/final_project/feed_data/station_information'")
  println("Database deployment:")
  println(" Created station_information table in s19909_bixi_feed_minhle database")

  //SYSTEM_INFORMATION
  stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.system_information")
  stmt.execute("CREATE TABLE s19909_bixi_feed_minhle.system_information (system_id STRING, " +
    "language STRING," +
    "name STRING," +
    "short_name STRING," +
    "operator STRING," +
    "url STRING," +
    "purchase_url STRING," +
    "start_date DATE," +
    "phone_number STRING," +
    "email STRING," +
    "license_url STRING," +
    "timezone STRING)" +
    "ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE " +
    "LOCATION '/user/fall2019/minhle/final_project/feed_data/system_information'")
  println(" Created system_information table in s19909_bixi_feed_minhle database")

  //SYSTEM_ALERTS
  stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.system_alerts")
  stmt.execute("CREATE TABLE s19909_bixi_feed_minhle.system_alerts (alert_id STRING, " +
    "type STRING," +
    "url STRING," +
    "summary STRING," +
    "description STRING," +
    "last_updated DOUBLE)" +
    "ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE " +
    "LOCATION '/user/fall2019/minhle/final_project/feed_data/system_alerts'")
  println(" Created system_alerts table in s19909_bixi_feed_minhle database")

  //STATION_STATUS
  stmt.execute("DROP TABLE IF EXISTS s19909_bixi_feed_minhle.station_status")
  stmt.execute("CREATE TABLE s19909_bixi_feed_minhle.station_status (station_id STRING, " +
    "num_bikes_available INT," +
    "num_ebikes_available INT," +
    "num_bikes_disabled INT," +
    "num_docks_available INT," +
    "num_docks_disabled INT," +
    "is_installed INT," +
    "is_renting INT," +
    "is_returning INT," +
    "last_reported DOUBLE," +
    "eightd_has_available_keys   boolean," +
    "is_charging boolean)" +
    "ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE " +
    "LOCATION '/user/fall2019/minhle/final_project/feed_data/station_status'")
  println(" Created station_status table in s19909_bixi_feed_minhle database")
  }
}

