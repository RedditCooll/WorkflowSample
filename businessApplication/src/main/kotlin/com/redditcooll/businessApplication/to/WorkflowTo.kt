package com.redditcooll.businessApplication.to

/** define the variables **/
class WorkflowTo {
    var userList: List<String>? = null
    var decision: Boolean? = false
    var managerName: String? = null
    var isApprovalNeeded: Boolean? = false
    var businessContent: BusinessContent? = null
}

class BusinessContent {
    var content: String? = null
    var contactor: String? = null
}