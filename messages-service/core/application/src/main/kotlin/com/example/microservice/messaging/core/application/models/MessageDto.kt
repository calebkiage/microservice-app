package com.example.microservice.messaging.core.application.models

import java.time.Instant

open class MessageDto(open var content: String? = null, open var id: Long? = null, open var sentOn: Instant? = null)
