package com.example.microservice.messages.adapters.controllers.web.handlers

import com.example.microservice.messages.adapters.controllers.web.models.GetMessagesParameters
import com.example.microservice.messages.adapters.controllers.web.models.ValidatedMessageDto
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.usecases.SendUseCase
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import com.example.microservice.messaging.core.application.usecases.EditUseCase
import com.example.microservice.messaging.core.application.usecases.ViewUseCase
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/messages")
class RestMessagesController(
    private val auditor: MessageAuditor,
    private val editUseCase: EditUseCase,
    private val viewUseCase: ViewUseCase,
    private val sendUseCase: SendUseCase
) {
    @GetMapping
    fun getMessages(@ModelAttribute params: GetMessagesParameters): List<MessageDto> {
        return this.viewUseCase.fetchMessages()
    }

    @GetMapping("audit")
    fun getMessageChanges(): List<Any> {
        return this.auditor.viewHistory()
    }

    @PostMapping("send/{userId}")
    fun sendMessage(@PathVariable userId: String, @Validated @RequestBody message: ValidatedMessageDto): MessageDto {
        return this.sendUseCase.sendMessage(message)
    }

    @PutMapping("{messageId}")
    fun updateMessage(@PathVariable messageId: Long, @Validated @RequestBody message: ValidatedMessageDto): MessageDto {
        return this.editUseCase.editMessage(messageId, message.content.orEmpty())
    }
}
