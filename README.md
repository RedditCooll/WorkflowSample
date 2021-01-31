# WorkflowSample
There are many ways to implement workflow by Camunda (or other workflow engines),  this project is trying to demonstrate the workflow example by using REST API offered by Camunda.
Business application owns its business service. The workflow engine is an independent service, controlling the workflow status. Business application through calling REST API to set up the workflows as it needs. 

Basically, following below we can have the workflow:
1. define the BPMN (or others which the engine supports)
2. deploy the definied workflow
3. start the process instances with varialbes
4. manipulate the workflow

[Camunda 7.14](https://docs.camunda.org/manual/7.14/)


-- work in process -- 