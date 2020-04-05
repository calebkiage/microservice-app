package com.example.microservice.messaging.domain.entities

import java.time.Clock
import java.time.Instant

open class Message(var content: String, var clock: Clock = Clock.systemDefaultZone()) {
    open var id: Long? = null
    var sentOn: Instant? = null

    init {
        this.validateContent(this.content)
        this.content = this.sanitizeMessage(this.content)
    }

    @Throws(IllegalArgumentException::class)
    fun editContent(content: String) {
        this.validateContent(content)

        // Naive implementation
        this.content = this.sanitizeMessage(content)
    }

    @Throws(IllegalStateException::class)
    fun send() {
        if (this.sentOn != null) {
            throw IllegalStateException("This message has already been sent.")
        }

        // Update the sentOn field when we send a message
        this.sentOn = Instant.now(this.clock)
    }

    @Throws(IllegalArgumentException::class)
    private fun validateContent(content: String): Boolean {
        if (content.isBlank()) {
            throw IllegalArgumentException("The content cannot be null or blank")
        }

        return true
    }

    private fun sanitizeMessage(message: String): String {
        return message.trim()
    }
}
