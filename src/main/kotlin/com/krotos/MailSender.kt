package com.krotos

import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

const val USERNAME = "707f995816c1fc"
const val PASSWORD = "de594d45c59449"

class MailSender {

    private val session = prepareSession()

    fun send(mail: Mail) {
        val message = MimeMessage(session)
        message.setFrom(mail.from)
        message.addRecipient(Message.RecipientType.TO, InternetAddress(mail.to))
        message.subject = mail.subject
        message.setText(mail.messageContent)

        try {
            Transport.send(message)
            println("message sent successfully....")
        } catch (ex: MessagingException) {
            println("message NOT sent....")
            ex.printStackTrace()
        }
    }

    private fun prepareSession(): Session {
        val properties = Properties()
        properties["mail.smtp.auth"] = true
        properties["mail.smtp.host"] = "smtp.mailtrap.io"
        properties["mail.smtp.port"] = "2525"
        properties["mail.smtp.ssl.trust"] = "smtp.mailtrap.io"

        return Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(USERNAME, PASSWORD)
            }
        })
    }
}
