package com.redditcooll.businessApplication.service

import com.redditcooll.businessApplication.configuration.RestTemplateClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.SerializationUtils
import java.util.*


@Service
class WorkflowService {

    // TODO: implement return

    @Autowired
    lateinit var restTemplateClient: RestTemplateClient

    val baseUrl = "http://localhost:8080/engine-rest"

    fun deploymentProcess() {
        // TODO: implement function
    }

    fun startProcessInstance(processDefinitionId: String){
        var url = "$baseUrl/process-definition/$processDefinitionId/start"
        var requestBody = "{ \"businessKey\": \"none\", " +
                "\"variables\": { \"managerName\": { \"value\": \"User3\", \"type\": \"String\" }, " +
                "\"isTaskFinished\": { \"value\": false, \"type\": \"Boolean\" }," +
                "\"userList\": { \"value\": [\"USER1\", \"USER2\"], " +
                "\"valueInfo\": { \"objectTypeName\": \"java.util.ArrayList\", " +
                "\"serializationDataFormat\": \"application/json\" } } } }"
        restTemplateClient.postFormRequestBody(requestBody, url, null)
    }

    fun getProcessInstanceVariable(processInstanceId: String, varName: String){
        var url = "$baseUrl/process-instance/$processInstanceId/variables/$varName?deserializeValue=true"
        var paramMap = mutableMapOf<String,String>()
        paramMap["id"] = processInstanceId
        paramMap["varName"] = varName
        restTemplateClient.getForm(paramMap , url,null)
    }

    fun getProcessInstanceVariables(processInstanceId: String){
        var url = "$baseUrl/process-instance/$processInstanceId/variables?deserializeValue=true"
        restTemplateClient.getString(url,null)
    }

    fun setProcessInstanceVariables(processInstanceId: String, varName: String, serializeString: String){
        var url = "$baseUrl/process-instance/$processInstanceId/variables/$varName"
        var requestBody = "{\n" +
                "  \"value\" : \"$serializeString\",\n" +
                "  \"type\" : \"Object\",\n" +
                "  \"valueInfo\" : {\n" +
                "    \"objectTypeName\": \"java.util.ArrayList\",\n" +
                "    \"serializationDataFormat\": \"application/x-java-serialized-object\"\n" +
                "  }\n" +
                "}"
        restTemplateClient.putForm(requestBody, url, null)
    }

    fun completeTask(taskId: String){
        var url = "$baseUrl/task/$taskId/complete"
        var requestBody = "{\n" +
                "    \"variables\":\n" +
                "    {\"isTaskFinished\": {\"value\": true}},\n" +
                " \"withVariablesInReturn\": true\n" +
                "}"
        restTemplateClient.postFormRequestBody(requestBody,url,null)
    }

    /** for object type **/
    fun serializeObject(objet: Any): String{
        // serialize
        var serialization = SerializationUtils.serialize(objet)
        // Base64 encode
        return Base64.getEncoder().encodeToString(serialization)
    }

    fun deserializeObject(encodedSerialization: String): Any? {
        // Base64 decode
        var decode = Base64.getDecoder().decode(encodedSerialization)
        // deserialize
        return SerializationUtils.deserialize(decode)
    }
}