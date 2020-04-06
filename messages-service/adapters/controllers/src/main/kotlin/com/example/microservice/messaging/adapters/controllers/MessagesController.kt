package com.example.microservice.messaging.adapters.controllers

import com.example.microservice.messaging.adapters.controllers.mappers.WebMessageMapper
import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import com.example.microservice.messaging.application.ports.SendUseCaseInputPort

open class MessagesController(private val sendUseCase: SendUseCaseInputPort, private val messageMapper: WebMessageMapper) {
    open fun sendMessage(message: ExtMessage): ExtMessage {
        val dto = this.messageMapper.extMessageToMessageDto(message)
        return this.messageMapper.messageDtoToExtMessage(this.sendUseCase.sendMessage(dto))
    }
}
