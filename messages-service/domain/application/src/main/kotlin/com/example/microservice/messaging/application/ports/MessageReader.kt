package com.example.microservice.messaging.application.ports

import com.example.microservice.messaging.application.data.PersistentMessage

interface MessageReader {
    fun findAll(): List<PersistentMessage>
}
