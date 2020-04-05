package com.example.microservice.messaging.domain.usecases.send

import com.example.microservice.messaging.domain.entities.Message

class SendUseCase {
    fun sendMessage(message: Message): Unit {
        // TODO: Apply application logic
        message.send()
    }
}
