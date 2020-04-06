package com.example.microservice.messaging.application.ports

import com.example.microservice.messaging.application.data.PersistentMessage
import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.entities.Message

interface MessageMapper {
    fun convertToMessage(messageDto: MessageDto): Message
    fun convertToMessageDbObj(message: Message): PersistentMessage
    fun convertToMessageInput(message: Message): MessageDto
    fun persistentMessageToMessageDto(message: PersistentMessage): MessageDto
}
