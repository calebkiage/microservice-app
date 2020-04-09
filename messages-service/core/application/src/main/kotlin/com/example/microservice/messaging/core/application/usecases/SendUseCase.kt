package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.models.MessageDto

interface SendUseCase {
    fun sendMessage(input: MessageDto): MessageDto
}
