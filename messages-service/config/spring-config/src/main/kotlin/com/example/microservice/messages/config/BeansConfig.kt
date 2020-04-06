package com.example.microservice.messages.config

import com.example.microservice.messaging.adapters.controllers.MessagesController
import com.example.microservice.messaging.adapters.controllers.mappers.WebMessageMapper
import com.example.microservice.messaging.adapters.database.SpringJdbcMessageRepository
import com.example.microservice.messaging.application.ports.MessageMapper
import com.example.microservice.messaging.application.ports.MessageWriter
import com.example.microservice.messaging.application.ports.SendUseCaseInputPort
import com.example.microservice.messaging.application.send.SendUseCase
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.JdbcTemplate

class BeansConfig {
    @Bean
    fun messageWriter(jdbcTemplate: JdbcTemplate): MessageWriter {
        return SpringJdbcMessageRepository(jdbcTemplate)
    }

    @Bean
    @Primary
    fun messageMapper(): MessageMapper {
        return Mappers.getMapper(WebMessageMapper::class.java)
    }

    @Bean
    fun webMessageMapper(): WebMessageMapper {
        return Mappers.getMapper(WebMessageMapper::class.java)
    }

    @Bean
    fun messagesController(useCase: SendUseCaseInputPort, mapper: WebMessageMapper): MessagesController {
        return MessagesController(useCase, mapper)
    }

    @Bean
    fun sendUseCaseInputPort(writer: MessageWriter, mapper: MessageMapper): SendUseCaseInputPort {
        return SendUseCase(writer, mapper)
    }
}
