package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import com.example.microservice.messaging.core.application.ports.store.MessageWriter

class SendUseCaseImpl(
    private val messageAuditor: MessageAuditor,
    private val messageWriter: MessageWriter,
    private val messageMapper: MessageMapper
) : SendUseCase {
    override fun sendMessage(input: MessageDto): MessageDto {
        val message = this.messageMapper.messageDtoToMessage(input)
        // TODOs: Apply application logic
        message.send()

        var persistentMessage = this.messageMapper.messageToPersistentMessage(message)
        persistentMessage = this.messageWriter.save(persistentMessage)
        val result = this.messageMapper.persistentMessageToMessageDto(persistentMessage)

        this.messageAuditor.recordChange("unknown", result)
        return result
    }
}
