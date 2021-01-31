package com.redditcooll.businessApplication.controller

import com.redditcooll.businessApplication.service.BusinessService
import com.redditcooll.businessApplication.to.WorkflowTo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
class BusinessRest {
    @Autowired
    private lateinit var businessService: BusinessService

    @RequestMapping(value= ["/business/content"], method = [RequestMethod.POST], produces = ["application/json"])
    fun getBusinessContent(@RequestBody() workflowTo: WorkflowTo, request: HttpServletRequest): String {
        return businessService.getBusinessContent()
    }
}