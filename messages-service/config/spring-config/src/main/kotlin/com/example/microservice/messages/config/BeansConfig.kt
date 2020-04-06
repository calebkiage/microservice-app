package com.example.microservice.messages.config

import com.example.microservice.messaging.adapters.controllers.MessagesController
import com.example.microservice.messaging.adapters.controllers.mappers.WebMessageMapper
import com.example.microservice.messaging.adapters.database.MemoryMessageRepository
import com.example.microservice.messaging.adapters.database.SpringJdbcMessageRepository
import com.example.microservice.messaging.application.ports.MessageMapper
import com.example.microservice.messaging.application.ports.MessageReader
import com.example.microservice.messaging.application.ports.MessageStore
import com.example.microservice.messaging.application.ports.MessageWriter
import com.example.microservice.messaging.application.ports.SendUseCaseInputPort
import com.example.microservice.messaging.application.ports.ViewUseCaseInputPort
import com.example.microservice.messaging.application.usecases.SendUseCase
import com.example.microservice.messaging.application.usecases.ViewUseCase
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate

class BeansConfig {
    @Bean
    @Profile("!dev-solo")
    fun messageStore(jdbcTemplate: JdbcTemplate): MessageStore {
        return SpringJdbcMessageRepository(jdbcTemplate)
    }

    @Bean
    @Profile("dev-solo")
    fun memoryMessageStore(): MessageStore {
        return MemoryMessageRepository()
    }

    @Bean
    fun webMessageMapper(): WebMessageMapper {
        return Mappers.getMapper(WebMessageMapper::class.java)
    }

    @Bean
    fun messagesController(
        sendUseCase: SendUseCaseInputPort,
        viewUseCase: ViewUseCaseInputPort,
        mapper: WebMessageMapper
    ): MessagesController {
        return MessagesController(sendUseCase, viewUseCase, mapper)
    }

    @Bean
    fun sendUseCase(writer: MessageWriter, mapper: MessageMapper): SendUseCaseInputPort {
        return SendUseCase(writer, mapper)
    }

    @Bean
    fun viewUseCase(reader: MessageReader, mapper: MessageMapper): ViewUseCaseInputPort {
        return ViewUseCase(reader, mapper)
    }
}
