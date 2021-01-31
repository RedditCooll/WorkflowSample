package com.redditcooll.workflow.service;

import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ProcessRequestDelegate implements JavaDelegate {

    // TODO: add logger & implement function
    private final static Logger LOGGER = Logger.getLogger("BusinessApplication");

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Business rule check: '" + execution.getVariable("isApprovalNeeded") + "'...");
    }

}
