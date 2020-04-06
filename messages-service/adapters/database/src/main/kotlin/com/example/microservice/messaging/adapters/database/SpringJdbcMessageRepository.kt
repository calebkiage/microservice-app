package com.example.microservice.messaging.adapters.database

import com.example.microservice.messaging.application.ports.store.MessageWriter
import com.example.microservice.messaging.application.data.PersistentMessage
import com.example.microservice.messaging.application.ports.store.MessageReader
import com.example.microservice.messaging.application.ports.store.MessageStore
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder

class SpringJdbcMessageRepository(
    private val jdbcTemplate: JdbcTemplate
) : MessageStore, MessageWriter, MessageReader {
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

    override fun findAll(): List<PersistentMessage> {
        return this.jdbcTemplate.query("select * from messages") { rs, _ -> PersistentMessage(rs.getString("content"), rs.getLong("id"))}
    }
}
