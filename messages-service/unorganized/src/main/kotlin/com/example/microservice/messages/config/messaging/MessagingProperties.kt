package com.example.microservice.messages.config.messaging

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@ConstructorBinding
@ConfigurationProperties("app.messaging")
@Validated
data class MessagingProperties(val topics: AppTopics) {
    data class AppTopics(@NotEmpty val unreadMessages: String)
}
