package com.example.microservice.messaging.core.application.ports.store

import com.example.microservice.messaging.core.application.models.MessageDto

interface MessageAuditor {
    fun recordChange(author: String, new: MessageDto, old: MessageDto)

    fun recordChange(author: String, new: MessageDto)

    fun viewHistory(size: Int = 5): List<Any>
}
