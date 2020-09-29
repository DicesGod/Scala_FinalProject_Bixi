package ca.mcit.sprint2

import java.io.{BufferedWriter, File, FileWriter}

class GetJSONData {
  //Download and create file
  def createCSVFiles(fileName: String, json_data: String): Any = {
    val file = new File("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/" + fileName + ".csv")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(json_data)
    println(" Downloaded json_data of " + fileName + " to local and converted to CSV")
    bw.close()
  }

  //Convert JSON_data to CSV files
  def convertStation_information: Any = {
    try {
      println("Download the Feed:")
      //Download json_data of station_information
      val fileName = "station_information"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/station_information.json")
      var json_string = json_data.getLines().mkString("\n")

      json_string =  json_string.substring(json_string.indexOf("stations") + 13)
        .replace("{", "")
        .replace("}", "\n")
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
        .replace("[CREDITCARD,KEY]", "[CREDITCARD|KEY]")
        .dropRight(1).dropRight(2)
        .toString

      createCSVFiles(fileName, json_string)
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

      createCSVFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }
}








