package com.example.microservice.messages.config.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Exchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.boot.autoconfigure.amqp.RabbitProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(MessagingProperties::class)
class MessagingConfig {
    @Bean
    fun messageConverter(objectMapper: ObjectMapper): MessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun appExchange(properties: RabbitProperties): Exchange {
        return TopicExchange(properties.template.exchange, true, false)
    }

    @Bean
    fun unreadMessagesQueue(properties: MessagingProperties): Queue {
        return Queue("UnreadMessages", true, false, false)
    }

    @Bean
    fun binding(exchange: TopicExchange, queue: Queue, properties: MessagingProperties): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(properties.routingKeys.unreadMessages)
    }
}
