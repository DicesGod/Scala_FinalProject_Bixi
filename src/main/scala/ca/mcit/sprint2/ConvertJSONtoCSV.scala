package ca.mcit.sprint2

import java.io.{File, FileOutputStream}
import com.github.agourlay.json2Csv.Json2Csv

class ConvertJSONtoCSV {
  def convertJSONtoCSV: Any = {
    val output1 = new FileOutputStream("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/station_information.csv")
    Json2Csv.convert(new File("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/station_information.json"), output1) match {
      case Right(nb) => println(s" $nb CSV lines written to 'station_information.csv'")
      case Left(e) => println(s" Something bad happened $e")
    }

    val output2 = new FileOutputStream("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/system_information.csv")
    Json2Csv.convert(new File("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/system_information.json"), output2) match {
      case Right(nb) => println(s" $nb CSV lines written to 'system_information.csv'")
      case Left(e) => println(s" Something bad happened $e")
    }
  }
}
