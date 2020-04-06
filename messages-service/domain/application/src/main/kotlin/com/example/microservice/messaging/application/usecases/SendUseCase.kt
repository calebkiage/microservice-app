package com.example.microservice.messaging.application.usecases

import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.application.ports.MessageMapper
import com.example.microservice.messaging.application.ports.store.MessageWriter
import com.example.microservice.messaging.application.ports.send.SendUseCaseInputPort

class SendUseCase(
    private val messageWriter: MessageWriter,
    private val messageMapper: MessageMapper
) : SendUseCaseInputPort {
    override fun sendMessage(input: MessageDto): MessageDto {
        val message = this.messageMapper.messageDtoToMessage(input)
        // TODOs: Apply application logic
        message.send()
        val msg = this.messageWriter.save(this.messageMapper.messageToPersistentMessage(message))
        return this.messageMapper.persistentMessageToMessageDto(msg)
    }
}
