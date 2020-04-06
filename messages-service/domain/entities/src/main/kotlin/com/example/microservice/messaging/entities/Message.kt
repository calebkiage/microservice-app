package com.example.microservice.messaging.entities

import java.time.Clock
import java.time.Instant

open class Message private constructor(
    var content: String?,
    var id: Long?,
    var sentOn: Instant?,
    private val clock: Clock = Clock.systemDefaultZone()
) {
    private constructor() : this(null, null, null)

    companion object {
        fun builder(): Builder {
            return Builder()
        }

        private fun sanitizeMessage(message: String): String {
            return message.trim()
        }

        @Throws(IllegalArgumentException::class)
        private fun validateContent(content: String?): Boolean {
            if (content.isNullOrBlank()) {
                throw IllegalArgumentException("The content cannot be null or blank")
            }

            return true
        }
    }

    @Throws(IllegalArgumentException::class)
    fun editContent(content: String) {
        validateContent(content)
        this.content = sanitizeMessage(content)
    }

    @Throws(IllegalStateException::class)
    fun send() {
        if (this.sentOn != null) {
            throw IllegalStateException("This message has already been sent.")
        }

        // Update the sentOn field when we send a message
        this.sentOn = Instant.now(this.clock)
    }

    open class Builder constructor() {
        private var content: String? = null
        private var id: Long? = null
        private var sentOn: Instant? = null
        private var clock: Clock = Clock.systemDefaultZone()

        constructor(message: Message) : this() {
            this.content = message.content
            this.id = message.id
            this.sentOn = message.sentOn
            this.clock = message.clock
        }

        fun content(value: String?): Builder {
            validateContent(value)
            this.content = value?.let { sanitizeMessage(it) }
            return this
        }

        fun id(value: Long?): Builder {
            this.id = value
            return this
        }

        fun sentOn(value: Instant?): Builder {
            this.sentOn = value
            return this
        }

        fun clock(value: Clock): Builder {
            this.clock = value
            return this
        }

        fun build(): Message {
            return Message(this.content, this.id, this.sentOn, this.clock)
        }
    }
}
