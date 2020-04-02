package com.example.microservice.messages.services

import com.example.microservice.core.models.PageInfo
import com.example.microservice.core.models.PagedResult
import com.example.microservice.core.models.messaging.UnreadMessageNotification
import com.example.microservice.messages.config.messaging.MessagingProperties
import com.example.microservice.messages.models.Message
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component

@Component
class MessagesService(
    private val amqpTemplate: AmqpTemplate,
    private val msgProperties: MessagingProperties
) {
    fun getMessages(
        fromUser: String?,
        toUser: String?,
        cursor: String? = null,
        limit: Int? = null
    ): PagedResult<Message> {
        return PagedResult(emptyList(), 0, PageInfo())
    }

    // Admin users can see a list of all messages?
    // TODO: Secure
    fun getMessages(cursor: String? = null, limit: Int? = null): PagedResult<Message> {
        return PagedResult(emptyList(), 0, PageInfo())
    }

    fun sendMessage(message: Message, userId: String) {
        // TODO: If message.threadId is null, create a new thread
        // TODO: Send a message

        // Notify of unread message
        val routingKey = this.msgProperties.routingKeys.unreadMessages
        val msg = UnreadMessageNotification(userId)
        this.amqpTemplate.convertAndSend(routingKey, msg)
    }
}
