package com.example.microservice.messaging.adapters.controllers.mappers

import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import com.example.microservice.messaging.application.models.EntityFactory
import com.example.microservice.messaging.application.models.MessageDto
import com.example.microservice.messaging.application.ports.MessageMapper
import org.mapstruct.Mapper

@Mapper(uses = [EntityFactory::class])
interface WebMessageMapper : MessageMapper {
    fun messageDtoToExtMessage(message: MessageDto): ExtMessage
    fun extMessageToMessageDto(message: ExtMessage): MessageDto
}
