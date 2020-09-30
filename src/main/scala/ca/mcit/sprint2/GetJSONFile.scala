package ca.mcit.sprint2

import java.io.File
import java.net.URL
import org.apache.commons.io.FileUtils

class GetJSONFile {
  //Get JSON files
  def getStation_information: Any = {
    try {
      //Download json_data of station_information
      val FILE_URL = "https://api-core.bixi.com/gbfs/en/station_information.json"
      val FILE_NAME = "Feed/station_information.json"

      FileUtils.copyURLToFile(
        new URL(FILE_URL),
        new File(FILE_NAME))

      println(" Downloaded json_data of " + FILE_NAME + " to local")
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def getSystem_information: Any = {
    try {
      println("Download the Feed:")
      //Download json_data of station_information
      val FILE_URL = "https://api-core.bixi.com/gbfs/en/system_information.json"
      val FILE_NAME = "Feed/system_information.json"

      FileUtils.copyURLToFile(
        new URL(FILE_URL),
        new File(FILE_NAME))

      println(" Downloaded json_data of " + FILE_NAME + " to local")
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }
}








