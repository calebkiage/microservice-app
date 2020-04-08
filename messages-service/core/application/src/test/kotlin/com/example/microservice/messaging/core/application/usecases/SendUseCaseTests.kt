package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.data.PersistentMessage
import com.example.microservice.messaging.core.application.ports.store.MessageWriter
import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.application.ports.store.MessageAuditor
import com.example.microservice.messaging.core.domain.Message
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@DisplayName("The send use case")
class SendUseCaseTests {
    private lateinit var messageAuditor: MessageAuditor
    private lateinit var messageMapper: MessageMapper
    private lateinit var messageWriter: MessageWriter
    private lateinit var sendUseCase: SendUseCase
    private val frozenClock = Clock.fixed(Instant.parse("2020-04-05T05:57:51.00Z"), ZoneId.systemDefault())

    @BeforeEach
    fun testSetup() {
        this.messageAuditor = mockk()
        this.messageMapper = mockk()
        this.messageWriter = mockk()
        this.sendUseCase = SendUseCase(this.messageAuditor, this.messageWriter, this.messageMapper)


        every { messageAuditor.recordChange(any(), any(), any()) }.answers {}
        every { messageMapper.messageDtoToMessage(any()) }.returns(Message.builder().content("test").build())
        every { messageMapper.messageToPersistentMessage(any()) }.returns(PersistentMessage())
        every { messageMapper.persistentMessageToMessageDto(any()) }.returns(MessageDto())
        every { messageWriter.save(any()) }.returns(PersistentMessage(id = 1))
    }

    @Nested
    @DisplayName("when sendMessage")
    inner class SendMessageTests {
        @Test
        fun `should set the message entity's sentOn date`() {
            // Arrange
            val messageToSend = MessageDto("")
            val message: Message = Message.builder().content("test").clock(frozenClock).build()
            every { messageMapper.messageDtoToMessage(any()) }.returns(message)

            // Act
            sendUseCase.sendMessage(messageToSend)

            // Assert
            val expected = Instant.parse("2020-04-05T05:57:51.00Z")
            Assertions.assertEquals(expected.toEpochMilli(), message.sentOn?.toEpochMilli())
        }

        @Test
        fun `should save the message to a store`() {
            // Arrange
            val messageToSend = MessageDto("")
            val dbObject = PersistentMessage()
            val message: Message = Message.builder().content("test").build()
            every { messageMapper.messageDtoToMessage(any()) }.returns(message)
            every { messageMapper.messageToPersistentMessage(any()) }.returns(dbObject)

            // Act
            sendUseCase.sendMessage(messageToSend)

            // Assert
            // Saving means calling a store provider's save function
            verify(exactly = 1) { messageWriter.save(dbObject) }
        }
    }
}
