package com.example.microservice.messaging.adapters.database

import com.example.microservice.messaging.application.ports.MessageWriter
import com.example.microservice.messaging.application.data.PersistentMessage
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder

class SpringJdbcMessageRepository(
    private val jdbcTemplate: JdbcTemplate
) : MessageWriter {
    override fun save(persistentMessage: PersistentMessage): PersistentMessage {
        val insertSql = "insert into messages (content, sentOn) values(?, ?)"

        val keyHolder = GeneratedKeyHolder()
        this.jdbcTemplate.update({
            it.prepareStatement(insertSql, arrayOf("id"))
                .apply {
                    setString(1, persistentMessage.content)
                    setObject(1, persistentMessage.sentOn)
                }
        }, keyHolder)

        return PersistentMessage(persistentMessage.content, keyHolder.key?.toLong(), persistentMessage.sentOn)
    }
}
