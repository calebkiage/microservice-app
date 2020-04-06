package com.example.microservice.messaging.application.ports.store

import com.example.microservice.messaging.application.data.PersistentMessage

interface MessageWriter {
    fun save(persistentMessage: PersistentMessage): PersistentMessage
}
