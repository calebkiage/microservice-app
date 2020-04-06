package com.example.microservice.messaging.application.ports

import com.example.microservice.messaging.application.models.MessageDto

interface SendUseCaseInputPort {
    fun sendMessage(input: MessageDto): MessageDto
}
