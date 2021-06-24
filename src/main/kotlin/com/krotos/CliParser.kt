package com.krotos

import org.apache.commons.cli.*
import kotlin.system.exitProcess

enum class CliParams(
    val shortOption: String,
    val longOption: String,
    val hasArgs: Boolean,
    val description: String,
    val required: Boolean = false
) {
    RECEIVER("r", "receiver", true, "receiver email address", true),
    MESSAGE("m", "message", true, "message content", true),
    SENDER("s", "sender", true, "sender email address"),
    SUBJECT("u", "subject", true, "email subject"),
}

class CliParser {

    private val options = Options()
    private val parser: CommandLineParser = DefaultParser()
    private val formatter by lazy {
        HelpFormatter()
    }
    lateinit var cmd: CommandLine

    init {
        CliParams.values().forEach {
            val option = Option(it.shortOption, it.longOption, it.hasArgs, it.description)
            option.isRequired = it.required
            options.addOption(option)
        }
    }

    fun parse(args: Array<String>) {
        cmd = try {
            parser.parse(options, args)
        } catch (e: ParseException) {
            println(e.message)
            formatter.printHelp("MailSender", options)
            exitProcess(1)
        }
    }

    fun getValue(option: CliParams): String? {
        return cmd.getOptionValue(option.longOption)
    }
}
