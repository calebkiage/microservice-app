package com.example.microservice.messaging.core.application.mappers

import com.example.microservice.messaging.core.application.data.PersistentMessage
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.domain.Message

class ManualMessageMapper : MessageMapper {
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
