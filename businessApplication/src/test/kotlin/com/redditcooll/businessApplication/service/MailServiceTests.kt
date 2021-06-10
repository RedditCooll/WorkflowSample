package com.redditcooll.businessApplication.service

import com.redditcooll.businessApplication.to.MailTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MailServiceTests {
    @Autowired
    private lateinit var mailService: MailService

    @Test
    fun makeMailContentByTemplateTest() {
        var mailToTest = MailTo()
        mailToTest.toMail = "user1@gmail.com"
        mailToTest.toName = "User1"
        mailToTest.fromMail = "user2@gmail.com"
        mailToTest.fromName = "User2"
        mailToTest.ccMail = "user3@gmail.com;user4@gmail.com"
        mailToTest.ccName = "User3, User4"
        mailToTest.subject = "This is the test mail"
        mailToTest.contentText = "Some messages here..."
        mailToTest.contentMap = mutableMapOf("link" to "test.abc.com")
        mailToTest.contentListMap = mutableListOf(
            mutableMapOf(
                "attri_1" to "this is attri_1",
                "attri_2" to "this is attri_2"))

        var mailtmeplateStr = mailService.makeMailContentByTemplate(mailToTest, "template.ftl")
        print(mailtmeplateStr)
    }

}