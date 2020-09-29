package ca.mcit.sprint2

object Main extends App{
  val databaseDeployment = new DatabaseDeployment
  val getJSONData = new GetJSONData
  val fileManagement = new FileManagement
  val directoriesManagement = new DirectoriesManagement

  databaseDeployment.createDatabase
  databaseDeployment.createEnrichedTb

  directoriesManagement.createDirectories

  getJSONData.convertStation_information
  getJSONData.convertSystem_information

  val enrichData = new EnrichData
  enrichData.enrich_sta_sys_info

  fileManagement.uploadFiles
}
