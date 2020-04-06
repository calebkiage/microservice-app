package com.example.microservice.messaging.application.ports.store

import com.example.microservice.messaging.application.data.PersistentMessage

interface MessageReader {
    fun findAll(): List<PersistentMessage>
}
