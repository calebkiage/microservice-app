package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.models.MessageDto

interface EditUseCase {
    fun editMessage(id: Long, newContent: String): MessageDto
}
