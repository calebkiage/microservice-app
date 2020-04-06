package com.example.microservice.messages.drivers.web.handlers

import com.example.microservice.messages.drivers.web.models.GetMessagesParameters
import com.example.microservice.messaging.adapters.controllers.MessagesController
import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class RestMessagesController(private val messagesController: MessagesController) {
    @GetMapping
    fun getMessages(@ModelAttribute params: GetMessagesParameters): List<ExtMessage> {
        return this.messagesController.getMessages()
    }

    @PostMapping("send/{userId}")
    fun sendMessage(@PathVariable userId: String, @Validated @RequestBody message: ExtMessage): ExtMessage {
        return this.messagesController.sendMessage(message)
    }
}
