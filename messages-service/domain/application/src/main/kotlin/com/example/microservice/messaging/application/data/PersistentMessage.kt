package com.example.microservice.messaging.application.data

import java.time.Instant

// Goes to the database
open class PersistentMessage(var content: String? = null, open var id: Long? = null, var sentOn: Instant? = null)
