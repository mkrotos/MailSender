package com.krotos

fun main(args: Array<String>) {
    val cliParser = CliParser()
    cliParser.parse(args)

    val mail = Mail(
        cliParser.getValue(CliParams.RECEIVER)!!,
        cliParser.getValue(CliParams.SENDER) ?: "me@me.pl",
        cliParser.getValue(CliParams.SUBJECT) ?: "Default subject",
        cliParser.getValue(CliParams.MESSAGE)!!
    )

    val sender = MailSender()
    sender.send(mail)
}
