package com.example.microservice.messaging.application.ports

import com.example.microservice.messaging.application.data.PersistentMessage
import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.entities.Message

interface MessageMapper {
    fun messageDtoToMessage(messageDto: MessageDto): Message
    fun messageToPersistentMessage(message: Message): PersistentMessage
    fun messageToMessageDto(message: Message): MessageDto
    fun persistentMessageToMessageDto(message: PersistentMessage): MessageDto
}
