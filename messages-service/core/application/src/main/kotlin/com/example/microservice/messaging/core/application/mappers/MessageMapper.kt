package com.example.microservice.messaging.core.application.mappers

import com.example.microservice.messaging.core.application.data.PersistentMessage
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.domain.Message

interface MessageMapper {
    fun messageDtoToMessage(messageDto: MessageDto): Message
    fun messageToPersistentMessage(message: Message): PersistentMessage
    fun messageToMessageDto(message: Message): MessageDto
    fun persistentMessageToMessageDto(message: PersistentMessage): MessageDto
    fun persistentMessageToMessage(message: PersistentMessage): Message
}
