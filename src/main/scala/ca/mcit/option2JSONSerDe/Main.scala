package ca.mcit.option2JSONSerDe

import ca.mcit.Option1ConvertJSONtoText.FileManagement

object Main extends App{
  val databaseDeployment = new DatabaseDeployment2
  val getJSONData2 = new GetJSONData2
  val fileManagement = new FileManagement

  databaseDeployment.createDBTables

  getJSONData2.getStation_information
  getJSONData2.getStation_status
  getJSONData2.getSystem_alerts
  getJSONData2.getSystem_information

  fileManagement.uploadFiles
}