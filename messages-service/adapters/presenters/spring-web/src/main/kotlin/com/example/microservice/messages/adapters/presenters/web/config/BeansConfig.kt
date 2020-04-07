package com.example.microservice.messages.adapters.presenters.web.config

import com.example.microservice.messaging.adapters.gateways.data.audit.JaversAuditor
import com.example.microservice.messaging.adapters.gateways.data.jdbc.SpringJdbcMessageRepository
import com.example.microservice.messaging.adapters.gateways.data.mock.MemoryMessageRepository
import com.example.microservice.messaging.core.application.mappers.ManualMessageMapper
import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import com.example.microservice.messaging.core.application.ports.store.MessageReader
import com.example.microservice.messaging.core.application.ports.store.MessageStore
import com.example.microservice.messaging.core.application.ports.store.MessageWriter
import com.example.microservice.messaging.core.application.usecases.SendUseCase
import com.example.microservice.messaging.core.application.usecases.ViewUseCase
import org.springframework.context.annotation.Bean
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
    fun messageMapper(): MessageMapper {
        return ManualMessageMapper()
    }

    @Bean
    fun messageAuditor(): MessageAuditor {
        return JaversAuditor.instance()
    }

    @Bean
    fun sendUseCase(auditor: MessageAuditor, writer: MessageWriter, mapper: MessageMapper): SendUseCase {
        return SendUseCase(auditor, writer, mapper)
    }

    @Bean
    fun viewUseCase(reader: MessageReader, mapper: MessageMapper): ViewUseCase {
        return ViewUseCase(reader, mapper)
    }
}
