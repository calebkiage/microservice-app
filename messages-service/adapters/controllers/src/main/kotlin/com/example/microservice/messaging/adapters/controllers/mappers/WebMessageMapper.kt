package com.example.microservice.messaging.adapters.controllers.mappers

import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.application.ports.MessageMapper

interface WebMessageMapper : MessageMapper {
    fun messageDtoToExtMessage(message: MessageDto): ExtMessage

    fun extMessageToMessageDto(message: ExtMessage): MessageDto
}
