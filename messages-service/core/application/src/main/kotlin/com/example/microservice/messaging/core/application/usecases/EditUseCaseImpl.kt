package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import com.example.microservice.messaging.core.application.ports.store.MessageReader
import com.example.microservice.messaging.core.application.ports.store.MessageWriter

class EditUseCaseImpl(
    private val messageAuditor: MessageAuditor,
    private val messageReader: MessageReader,
    private val messageWriter: MessageWriter,
    private val messageMapper: MessageMapper
) : EditUseCase {
    override fun editMessage(id: Long, newContent: String): MessageDto {
        val message = this.messageReader.findById(id) ?: throw IllegalAccessException("Message not found")
        // TODOs: Apply application logic
        val entity = this.messageMapper.persistentMessageToMessage(message)
        entity.editContent(newContent)

        var persistentMessage = this.messageMapper.messageToPersistentMessage(entity)
        persistentMessage = this.messageWriter.save(persistentMessage)
        val result = this.messageMapper.persistentMessageToMessageDto(persistentMessage)

        this.messageAuditor.recordChange("unknown", result)
        return result
    }
}
