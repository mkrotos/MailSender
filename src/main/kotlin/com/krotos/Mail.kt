package com.krotos

data class Mail(
    val to: String,
    val from: String,
    val subject: String,
    val messageContent: String
)

class TestPojo(text: String) {
    val text: String

    init {
        this.text = text
    }
}