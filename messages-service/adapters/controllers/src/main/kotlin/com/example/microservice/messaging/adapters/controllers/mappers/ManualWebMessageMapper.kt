package com.example.microservice.messaging.adapters.controllers.mappers

import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import com.example.microservice.messaging.application.data.PersistentMessage
import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.entities.Message

class ManualWebMessageMapper : WebMessageMapper {
    override fun messageDtoToExtMessage(message: MessageDto): ExtMessage {
        return ExtMessage(message.content, message.id, message.sentOn)
    }

    override fun extMessageToMessageDto(message: ExtMessage): MessageDto {
        return MessageDto(message.content, message.id, message.sentOn)
    }

    override fun messageDtoToMessage(messageDto: MessageDto): Message {
        return Message.builder()
            .content(messageDto.content)
            .id(messageDto.id)
            .sentOn(messageDto.sentOn)
            .build()
    }

    override fun messageToPersistentMessage(message: Message): PersistentMessage {
        return PersistentMessage(message.content, message.id, message.sentOn)
    }

    override fun messageToMessageDto(message: Message): MessageDto {
        return MessageDto(message.content, message.id, message.sentOn)
    }

    override fun persistentMessageToMessageDto(message: PersistentMessage): MessageDto {
        return MessageDto(message.content, message.id, message.sentOn)
    }
}
