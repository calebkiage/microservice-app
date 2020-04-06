package com.example.microservice.messaging.application.models

import java.time.Instant

class MessageDto(var content: String? = null, var id: Long? = null, var sentOn: Instant? = null)
