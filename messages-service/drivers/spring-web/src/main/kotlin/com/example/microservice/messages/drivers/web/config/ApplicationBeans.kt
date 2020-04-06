package com.example.microservice.messages.drivers.web.config

import com.example.microservice.messages.config.BeansConfig
import com.example.microservice.messages.drivers.web.validators.MessageValidator
import com.example.microservice.messaging.adapters.controllers.models.ExtMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.validation.Validator

@Configuration
@Import(BeansConfig::class)
class ApplicationBeans {
    @Bean
    fun testBean(): Validator {
        return MessageValidator()
    }
}
