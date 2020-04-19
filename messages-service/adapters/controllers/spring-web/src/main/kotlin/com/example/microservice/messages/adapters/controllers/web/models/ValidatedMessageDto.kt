package com.example.microservice.messages.adapters.controllers.web.models

import com.example.microservice.messaging.core.application.models.MessageDto
import java.time.Instant
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ValidatedMessageDto(
    @get:NotBlank
    override var content: String? = null,
    @get:Min(1)
    override var id: Long? = null,
    override var sentOn: Instant? = null
) : MessageDto(content, id, sentOn) {
}
