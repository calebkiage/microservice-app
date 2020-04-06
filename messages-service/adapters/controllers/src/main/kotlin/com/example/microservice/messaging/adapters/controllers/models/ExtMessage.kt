package com.example.microservice.messaging.adapters.controllers.models

import com.example.microservice.messaging.application.models.MessageDto
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ExtMessage(
    @NotBlank
    override var content: String? = null,
    @Min(1)
    override var id: Long? = 0
) : MessageDto(null, null)
