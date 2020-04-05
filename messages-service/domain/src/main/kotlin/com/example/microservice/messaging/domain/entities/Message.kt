package com.example.microservice.messaging.domain.entities

import java.lang.IllegalArgumentException
import java.time.Clock
import java.time.Instant

open class Message(var content: String, var clock: Clock) {
    open var id: Long? = null
    var sentOn: Instant? = null

    fun editContent(content: String) {
        if (content.isBlank()) {
            throw IllegalArgumentException("The content cannot be null or blank")
        }

        // Naive implementation
        this.content = content.trim()
    }

    fun send() {
        if (this.sentOn != null) {
            throw IllegalStateException("This message has already been sent.")
        }

        // Update the sentOn field when we send a message
        this.sentOn = Instant.now(this.clock)
    }
}
