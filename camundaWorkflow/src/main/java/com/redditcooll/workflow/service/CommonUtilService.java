package com.redditcooll.workflow.service;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonUtilService {

    @Autowired
    private HistoryService historyService;

    // TODO: test the function
    public List<HistoricVariableInstance> getCompletedProcessInstanceVariables(String processDefinitionKey,
                                                                               String processInstanceBusinessKey,
                                                                               String processInstanceId){
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey)
                .processInstanceBusinessKey(processInstanceBusinessKey)
                .processInstanceId(processInstanceId)
                .finished().singleResult();
        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery()
                .processDefinitionKey(historicProcessInstance.getProcessDefinitionKey())
                .processInstanceId(historicProcessInstance.getId()).list();
        return historicVariableInstances;
    }
}
