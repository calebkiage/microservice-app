package com.example.microservice.messaging.application.ports.store

import com.example.microservice.messaging.application.ports.store.MessageReader
import com.example.microservice.messaging.application.ports.store.MessageWriter

interface MessageStore : MessageReader, MessageWriter
