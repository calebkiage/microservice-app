package com.example.microservice.messaging.core.application.ports.send

import com.example.microservice.messaging.core.application.models.MessageDto

interface SendUseCase {
    fun sendMessage(input: MessageDto): MessageDto
}
