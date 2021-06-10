package com.redditcooll.businessApplication.service

import com.redditcooll.businessApplication.BusinessApplication
import com.redditcooll.businessApplication.to.MailTo
import freemarker.template.Configuration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Service
class MailService {
    private val logger = LoggerFactory.getLogger(MailService::class.java)

    @Autowired
    private lateinit var sender: JavaMailSender

    fun makeMailContentByTemplate(mailTo: MailTo, templateName: String): String {
        logger.info("making mail content with template: $templateName")
        val config = Configuration(Configuration.VERSION_2_3_30)
        config.setClassForTemplateLoading(BusinessApplication::class.java, "/mailTemplates")
        config.defaultEncoding = "UTF-8"
        var freemarkerTemplate = config.getTemplate(templateName)
        return FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, mailTo)
    }

    fun sendMail(mailTo: MailTo, templateName: String) {
        var parsedTemplate = makeMailContentByTemplate(mailTo, templateName)
        try {
            val mimeMessage: MimeMessage = sender.createMimeMessage()
            val messageHelper = MimeMessageHelper(mimeMessage, true)
            messageHelper.setFrom(mailTo.fromMail!!)
            messageHelper.setTo(mailTo.toMail!!)
            messageHelper.setCc(mailTo.ccMail!!)
            messageHelper.setSubject(mailTo.subject!!)
            messageHelper.setText(parsedTemplate, true)
            sender.send(mimeMessage)
            logger.info("sent mail with $mailTo successfully!")
        } catch (e: MessagingException){
            logger.error("sent mail with $mailTo failed!", e)
        }
    }
}