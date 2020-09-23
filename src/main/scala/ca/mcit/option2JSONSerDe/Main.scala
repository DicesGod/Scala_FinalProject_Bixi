package ca.mcit.option2JSONSerDe

import ca.mcit.Option1ConvertJSONtoText.{FileManagement}

object Main extends App{
  val databaseDeployment = new DatabaseDeployment2
  val getJSONData = new GetJSONData2
  val fileManagement = new FileManagement

  databaseDeployment.createDBTables

  getJSONData.getStation_information
  getJSONData.getStation_status
  getJSONData.getSystem_alerts
  getJSONData.getSystem_information

  fileManagement.uploadFiles
}
