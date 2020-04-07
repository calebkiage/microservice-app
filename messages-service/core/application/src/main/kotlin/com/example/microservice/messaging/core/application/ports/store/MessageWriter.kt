package com.example.microservice.messaging.core.application.ports.store

import com.example.microservice.messaging.core.application.data.PersistentMessage

interface MessageWriter {
    fun save(persistentMessage: PersistentMessage): PersistentMessage
}
