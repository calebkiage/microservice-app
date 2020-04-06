package com.example.microservice.messaging.adapters.controllers.models

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class ExtMessage(
    @get:NotBlank
    var content: String? = null,
    @get:Min(1)
    var id: Long? = null
)
