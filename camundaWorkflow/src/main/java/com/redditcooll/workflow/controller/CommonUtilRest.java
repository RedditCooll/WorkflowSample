package com.redditcooll.workflow.controller;

import com.redditcooll.workflow.service.CommonUtilService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonUtilRest {

    @Autowired
    private CommonUtilService commonUtilService;

    @PostMapping(value = "/common/process-instance/completed/variables")
    @ResponseBody
    public List<HistoricVariableInstance> getCompletedProcessInstanceVariables(
            @RequestParam String processDefinitionKey, @RequestParam String processInstanceBusinessKey,
            @RequestParam String processInstanceId) {
        return commonUtilService.getCompletedProcessInstanceVariables(processDefinitionKey, processInstanceBusinessKey, processInstanceId);
    }

}
