package com.example.microservice.messages.drivers.web.validators

import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import org.springframework.validation.Errors
import org.springframework.validation.Validator

class MessageValidator : Validator {
    override fun validate(target: Any, errors: Errors) {
        val msg = target as ExtMessage?
        if (msg?.content.isNullOrBlank()) {
            errors.rejectValue("content", "person.empty")
        }
    }

    override fun supports(clazz: Class<*>): Boolean {
        return ExtMessage::class.java == clazz
    }
}
