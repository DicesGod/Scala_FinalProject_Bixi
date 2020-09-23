package ca.mcit.Option1ConvertJSONtoText

import java.io.{BufferedWriter, File, FileWriter}

class GetJSONData {
  //Download and create file
  def createTxtFiles(fileName: String, json_data: String): Any = {
    val file = new File("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/" + fileName + ".txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(json_data)
    println(" Downloaded json_data of " + fileName + " to local")
    bw.close()
  }

  //Convert JSON_data to text file
  def convertStation_information: Any = {
    try {
      println("Download the Feed:")
      //Download json_data of station_information
      val fileName = "station_information"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/station_information.json")
      var json_string = json_data.getLines().mkString("\n")

      json_string =  json_string.substring(json_string.indexOf("stations") + 13)
        .replace("{", "")
        .replace("}", ",\n")
        .replace("\"", "")
        .replace(",station_id:", "")
        .replace("station_id:", "")
        .replace("external_id:", "")
        .replace("short_name:", "")
        .replace("name:", "")
        .replace("lat:", "")
        .replace("lon:", "")
        .replace("rental_methods:", "")
        .replace("capacity:", "")
        .replace("electric_bike_surcharge_waiver:", "")
        .replace("is_charging:", "")
        .replace("eightd_has_key_dispenser:", "")
        .replace("has_kiosk:", "")
        .replace("[KEY,CREDITCARD]", "(KEY|CREDITCARD)")
        .dropRight(1).dropRight(2).dropRight(3)
        .toString

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def convertSystem_information: Any = {
    try {
      //Download json_data of station_information
      val fileName = "system_information"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/system_information.json")
      var json_string = json_data.getLines().mkString("\n")

      json_string = json_string.substring(json_string.indexOf("data\":") + 8)
        .replace("{", "")
        .replace("}", ",\n")
        .replace("\"", "")
        .replace("system_id:", "")
        .replace("language:", "")
        .replace("short_name:", "")
        .replace("name:", "")
        .replace("operator:", "")
        .replace("purchase_url:", "")
        .replace("license_url:", "")
        .replace("url:", "")
        .replace("start_date:", "")
        .replace("phone_number:", "")
        .replace("email:", "")
        .replace("timezone:", "")
        .dropRight(1).dropRight(2).dropRight(3)
        .toString

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def convertSystem_alerts: Any = {
    try {
      //Download json_data of station_information
      val fileName = "system_alerts"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/system_alerts.json")
      var json_string = json_data.getLines().mkString("\n")

      json_string = json_string.substring(json_string.indexOf("alerts\"") + 11)
        .replace("{", "")
        .replace("}", ",\n")
        .replace("\"", "")
        .replace("alert_id:", "")
        .replace("type:", "")
        .replace("url:", "")
        .replace("summary:", "")
        .replace("description:", "")
        .replace("last_updated:", "")
        .dropRight(1).dropRight(2).dropRight(3)
        .toString

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def convertStation_status: Any = {
    try {
      //Download json_data of station_information
      val fileName = "station_status"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/station_status.json")
      var json_string = json_data.getLines().mkString("\n")

      json_string = json_string.substring(json_string.indexOf("stations\"") + 13)
        .replace("{", "")
        .replace("},", ",\n")
        .replace("\"", "")
        .replace("station_id:", "")
        .replace("num_bikes_available:", "")
        .replace("num_ebikes_available:", "")
        .replace("num_bikes_disabled:", "")
        .replace("num_docks_available:", "")
        .replace("num_docks_disabled:", "")
        .replace("is_installed:", "")
        .replace("is_renting:", "")
        .replace("is_returning:", "")
        .replace("last_reported:", "")
        .replace("eightd_has_available_keys:", "")
        .replace("is_charging:", "")
        .dropRight(1).dropRight(2).dropRight(3)
        .toString

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }
}








