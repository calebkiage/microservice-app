package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.ports.store.MessageReader
import com.example.microservice.messaging.core.application.ports.view.ViewUseCase

class ViewUseCaseImpl(
    private val messageReader: MessageReader,
    private val messageMapper: MessageMapper
) : ViewUseCase {
    override fun fetchMessages(): List<MessageDto> {
        return this.messageReader.findAll().map { this.messageMapper.persistentMessageToMessageDto(it) }
    }
}
