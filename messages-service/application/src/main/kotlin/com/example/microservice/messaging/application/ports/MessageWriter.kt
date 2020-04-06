package com.example.microservice.messaging.application.ports

import com.example.microservice.messaging.application.data.PersistentMessage

interface MessageWriter {
    fun save(persistentMessage: PersistentMessage): PersistentMessage
}
