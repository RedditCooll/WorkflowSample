package com.redditcooll.businessApplication.to

class MailTo {
    var fromMail: String? = ""
    var fromName: String? = ""
    var toMail: String? = ""
    var toName: String? = ""
    var ccMail: String? = ""
    var ccName: String? = ""
    var subject: String? = ""
    var contentText: String? = ""
    var contentMap: Map<String, Any>? = null
    var contentListMap: MutableList<MutableMap<String, String>>? = null
}