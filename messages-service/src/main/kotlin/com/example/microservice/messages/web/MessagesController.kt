package com.example.microservice.messages.web

import com.example.microservice.core.models.PagedResult
import com.example.microservice.messages.models.GetMessageParameters
import com.example.microservice.messages.models.Message
import com.example.microservice.messages.services.MessagesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class MessagesController(private val messagesService: MessagesService) {
    @GetMapping
    fun getMessages(@ModelAttribute params: GetMessageParameters): PagedResult<Message> {
        if (params.from.isNullOrBlank() && params.to.isNullOrBlank()) {
            return this.messagesService.getMessages(params.after, params.first)
        }

        return this.messagesService.getMessages(params.from, params.to, params.after, params.first)
    }

    @PostMapping("send/{userId}")
    fun sendMessage(@PathVariable userId: String, @RequestBody message: Message) {
        return this.messagesService.sendMessage(message, userId)
    }
}
