package com.example.microservice.messaging.application.ports.view

import com.example.microservice.messaging.application.models.MessageDto

interface ViewUseCaseInputPort {
    fun fetchMessages(): List<MessageDto>
}
