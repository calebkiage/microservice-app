package com.example.microservice.messages.entrypoint.web.handlers

import com.example.microservice.messaging.adapters.controllers.MessagesController
import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class RestMessagesController(private val messagesController: MessagesController) {
//    @GetMapping
//    fun getMessages(@ModelAttribute params: GetMessageParameters): PagedResult<Message> {
//        if (params.from.isNullOrBlank() && params.to.isNullOrBlank()) {
//            return this.messagesService.getMessages(params.after, params.first)
//        }
//
//        return this.messagesService.getMessages(params.from, params.to, params.after, params.first)
//    }

    @PostMapping("send/{userId}")
    fun sendMessage(@PathVariable userId: String, @Validated @RequestBody message: ExtMessage): ExtMessage {
        return this.messagesController.sendMessage(message)
    }
}
