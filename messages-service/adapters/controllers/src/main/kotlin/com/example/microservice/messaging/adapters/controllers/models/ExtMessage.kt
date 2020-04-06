package com.example.microservice.messaging.adapters.controllers.models

import java.time.Instant
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class ExtMessage(
    @get:NotBlank
    val content: String?,

    @get:Min(1)
    val id: Long?,

    val sentOn: Instant?
)
