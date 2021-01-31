package com.redditcooll.businessApplication.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class WorkflowServiceTests {
    @Autowired
    private lateinit var workflowService: WorkflowService

    // TODO: add assert

    @Test
    fun startProcessInstanceTest() {
        var result = workflowService.startProcessInstance("Process_1ifhzix:1:f6b11872-5f22-11eb-9923-120852b31c7e")
        println(result)
    }

    @Test
    fun getProcessInstanceVariableTest() {
        workflowService.getProcessInstanceVariable("a44441c0-5f24-11eb-9923-120852b31c7e", "userList")
    }

    @Test
    fun getProcessInstanceVariablesTest() {
        workflowService.getProcessInstanceVariables("a44441c0-5f24-11eb-9923-120852b31c7e")
    }

    @Test
    fun setProcessInstanceVariables() {
        var testVal = arrayListOf<String>("James", "Wade")
        var serialization = workflowService.serializeObject(testVal)
        workflowService.setProcessInstanceVariables("a44441c0-5f24-11eb-9923-120852b31c7e", "userList", serialization)
    }

    @Test
    fun completeTaskTest() {
        workflowService.completeTask("59452634-5f23-11eb-9923-120852b31c7e")
    }

    @Test
    fun deserializationTest() {
        var arr = arrayListOf<String>("USER1", "USER2")
        var str = "rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAACdwQAAAACdAAFVVNFUjF0AAVVU0VSMng="
        var result = workflowService.deserializeObject(str)
        assertEquals(arr, result)
    }
}