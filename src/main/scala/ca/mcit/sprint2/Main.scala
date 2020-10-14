package ca.mcit.sprint2

object Main extends App{
  try {
    GetJSONFile.getSystemInformation
    GetJSONFile.getStationInformation

    ConvertJSONtoCSV.convertJSONtoCSV

    EnrichData.enrichStationsSystemInfo

    DatabaseDeployment.createDatabase
    DatabaseDeployment.createEnrichedTb

    DirectoriesManagement.createDirectories

    FileManagement.uploadFiles
  }
  catch {
    case _: Exception => println("Connection error! Please try again")
  }
}
