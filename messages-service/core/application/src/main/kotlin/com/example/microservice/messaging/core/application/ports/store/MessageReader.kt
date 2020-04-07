package com.example.microservice.messaging.core.application.ports.store

import com.example.microservice.messaging.core.application.data.PersistentMessage

interface MessageReader {
    fun findAll(): List<PersistentMessage>
}
