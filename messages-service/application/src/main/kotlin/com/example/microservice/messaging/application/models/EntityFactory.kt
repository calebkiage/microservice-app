package com.example.microservice.messaging.application.models

import com.example.microservice.messaging.entities.Message

open class EntityFactory {
    fun createMessage(): Message {
        // Workaround for https://github.com/mapstruct/mapstruct/issues/73
        return Message("__new__")
    }
}
