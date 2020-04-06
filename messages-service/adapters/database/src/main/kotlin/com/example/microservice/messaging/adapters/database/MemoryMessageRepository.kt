package com.example.microservice.messaging.adapters.database

import com.example.microservice.messaging.application.data.PersistentMessage
import com.example.microservice.messaging.application.ports.MessageWriter
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class MemoryMessageRepository : MessageWriter {
    private val store = ConcurrentHashMap<Long, PersistentMessage>()
    private val id = AtomicLong(0)

    override fun save(persistentMessage: PersistentMessage): PersistentMessage {
        // Generate id
        val id = this.id.incrementAndGet()
        persistentMessage.id = id

        this.store[id] = persistentMessage
        return persistentMessage
    }
}
