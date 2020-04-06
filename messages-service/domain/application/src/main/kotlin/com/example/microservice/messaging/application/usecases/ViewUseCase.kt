package com.example.microservice.messaging.application.usecases

import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.application.ports.MessageMapper
import com.example.microservice.messaging.application.ports.MessageReader
import com.example.microservice.messaging.application.ports.ViewUseCaseInputPort

class ViewUseCase(
    private val messageReader: MessageReader,
    private val messageMapper: MessageMapper
) : ViewUseCaseInputPort {
    override fun fetchMessages(): List<MessageDto> {
        return this.messageReader.findAll().map { this.messageMapper.persistentMessageToMessageDto(it) }
    }
}
