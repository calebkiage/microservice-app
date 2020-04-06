package com.example.microservice.messaging.application.send

import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.application.ports.MessageMapper
import com.example.microservice.messaging.application.ports.MessageWriter
import com.example.microservice.messaging.application.ports.SendUseCaseInputPort

class SendUseCase(
    private val messageWriter: MessageWriter,
    private val messageMapper: MessageMapper
) : SendUseCaseInputPort {
    override fun sendMessage(input: MessageDto): MessageDto {
        val message = this.messageMapper.convertToMessage(input)
        // TODOs: Apply application logic
        message.send()
        val msg = this.messageWriter.save(this.messageMapper.convertToMessageDbObj(message))
        return this.messageMapper.persistentMessageToMessageDto(msg)
    }
}
