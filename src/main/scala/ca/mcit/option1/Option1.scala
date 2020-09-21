package ca.mcit.option1

object Option1 extends App{
  //try {
    val databaseDeployment = new DatabaseDeployment
    val fileManagement = new FileManagement
    val getJSONData = new GetJSONData

    databaseDeployment.createDBTables

    getJSONData.convertStation_information
    getJSONData.convertSystem_information
    getJSONData.convertStation_status
    getJSONData.convertSystem_alerts

    fileManagement.uploadFiles
}
