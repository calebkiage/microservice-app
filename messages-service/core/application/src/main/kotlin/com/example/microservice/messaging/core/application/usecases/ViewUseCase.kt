package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.models.MessageDto

interface ViewUseCase {
    fun fetchMessages(): List<MessageDto>
}
