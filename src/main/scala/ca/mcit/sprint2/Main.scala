package ca.mcit.sprint2

object Main extends App{

  val getJSONFile = new GetJSONFile
  getJSONFile.getSystem_information
  getJSONFile.getStation_information

  val convertJSONtoCSV = new ConvertJSONtoCSV
  convertJSONtoCSV.convertJSONtoCSV

  val enrichData = new EnrichData
  enrichData.enrich_sta_sys_info

  val databaseDeployment = new DatabaseDeployment
  databaseDeployment.createDatabase
  databaseDeployment.createEnrichedTb

  val directoriesManagement = new DirectoriesManagement
  directoriesManagement.createDirectories

  val fileManagement = new FileManagement
  fileManagement.uploadFiles
}
