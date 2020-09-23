package ca.mcit.option2JSONSerDe

import java.io.{BufferedWriter, File, FileWriter}

class GetJSONData2 {
  //Download and create file
  def createTxtFiles(fileName: String, json_data: String): Any = {
    val file = new File("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/" + fileName + ".txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(json_data)
    println(" Downloaded json_data of " + fileName + " to local")
    bw.close()
  }

  //Get JSON data
  def getStation_information: Any = {
    try {
      println("Download the Feed:")
      //Download json_data of station_information
      val fileName = "station_information"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/station_information.json")
      var json_string = json_data.getLines().mkString

      json_string =  json_string.substring(json_string.indexOf("stations") + 11)
        .replace("},", "},\n")

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def getSystem_information: Any = {
    try {
      //Download json_data of station_information
      val fileName = "system_information"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/system_information.json")
      var json_string = json_data.getLines().mkString

      json_string = json_string.substring(json_string.indexOf("data\":") + 6)
        .replace("},", "},\n")

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def getSystem_alerts: Any = {
    try {
      //Download json_data of station_information
      val fileName = "system_alerts"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/system_alerts.json")
      var json_string = json_data.getLines().mkString

      json_string = json_string.substring(json_string.indexOf("alerts\"") + 9)
        .replace("},", "},\n")

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def getStation_status: Any = {
    try {
      //Download json_data of station_information
      val fileName = "station_status"
      val json_data = scala.io.Source.fromURL("https://api-core.bixi.com/gbfs/en/station_status.json")
      var json_string = json_data.getLines().mkString

      json_string = json_string.substring(json_string.indexOf("stations\"") + 11)
        .replace("},", "},\n")

      createTxtFiles(fileName, json_string)
      json_data.close()
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }
}








