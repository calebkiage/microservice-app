package com.example.microservice.messages.config.messaging

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(MessagingProperties::class)
class MessagingConfig {
    @Bean
    fun unreadTopic(props: MessagingProperties): NewTopic {
        return NewTopic(props.topics.unreadMessages, 1, 1)
    }
}
