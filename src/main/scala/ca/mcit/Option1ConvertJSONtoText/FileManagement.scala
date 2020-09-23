package ca.mcit.Option1ConvertJSONtoText

import ca.mcit.model.HadoopConnection
import org.apache.hadoop.fs.Path

class FileManagement {
  //uploadFile to HDFS
  def uploadFiles: Any = {
    try {
      println("File Management:")
      var filePath = new Path("/user/fall2019/minhle/final_project/feed_data/station_information/station_information.txt")
      var srcPath = new Path("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/station_information.txt")
      var dstPath = new Path("/user/fall2019/minhle/final_project/feed_data/station_information")
      uploadFile("station_information", filePath, srcPath, dstPath)

      filePath = new Path("/user/fall2019/minhle/final_project/feed_data/system_information/system_information.txt")
      srcPath = new Path("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/system_information.txt")
      dstPath = new Path("/user/fall2019/minhle/final_project/feed_data/system_information")
      uploadFile("system_information", filePath, srcPath, dstPath)

      filePath = new Path("/user/fall2019/minhle/final_project/feed_data/system_information/station_status.txt")
      srcPath = new Path("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/station_status.txt")
      dstPath = new Path("/user/fall2019/minhle/final_project/feed_data/station_status")
      uploadFile("station_status", filePath, srcPath, dstPath)

      filePath = new Path("/user/fall2019/minhle/final_project/feed_data/system_information/system_alerts.txt")
      srcPath = new Path("/Users/minhle/Desktop/Projects/Scala_FinalProject_Bixi/Feed/system_alerts.txt")
      dstPath = new Path("/user/fall2019/minhle/final_project/feed_data/system_alerts")
      uploadFile("system_alerts", filePath, srcPath, dstPath)
    }
    catch {
      case _: Exception => println("Connection error!")
    }
  }

  def uploadFile(fileName: String,filePath: Path,srcPath: Path,dstPath: Path): Any = {
    if (HadoopConnection.fs.exists (filePath))  {
    HadoopConnection.fs.delete (filePath, true)
    HadoopConnection.fs.copyFromLocalFile (srcPath,dstPath)
    println (" Deleted and uploaded the new "+ fileName+".txt file to HDFS")
    }
    else {
    HadoopConnection.fs.copyFromLocalFile (srcPath,dstPath)
    println (" Uploaded the new "+ fileName+".txt file to HDFS")
    }
  }
}


