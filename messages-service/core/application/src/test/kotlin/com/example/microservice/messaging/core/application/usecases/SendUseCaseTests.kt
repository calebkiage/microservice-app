package com.example.microservice.messaging.core.application.usecases

import com.example.microservice.messaging.core.application.data.PersistentMessage
import com.example.microservice.messaging.core.application.ports.store.MessageWriter
import com.example.microservice.messaging.core.application.mappers.MessageMapper
import com.example.microservice.messaging.core.application.models.MessageDto
import com.example.microservice.messaging.core.domain.Message
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("The send use case")
class SendUseCaseTests {
    private lateinit var messageMapper: MessageMapper
    private lateinit var messageWriter: MessageWriter
    private lateinit var sendUseCase: SendUseCase

    @BeforeEach
    fun testSetup() {
        this.messageMapper = mockk()
        this.messageWriter = mockk()
        this.sendUseCase = SendUseCase(this.messageWriter, this.messageMapper)


        every { messageMapper.messageDtoToMessage(any()) }.returns(Message.builder().content("test").build())
        every { messageMapper.messageToPersistentMessage(any()) }.returns(PersistentMessage())
        every { messageWriter.save(any()) }.returns(PersistentMessage(id = 1))
    }

    @Nested
    @DisplayName("when sendMessage")
    inner class SendMessageTests {
        @Test
        fun `should call the message entity's send function`() {
            // Arrange
            val messageToSend = MessageDto("")
            val message: Message = mockk()
            every { message.send() }.answers { }
            every { messageMapper.messageDtoToMessage(any()) }.returns(message)

            // Act
            sendUseCase.sendMessage(messageToSend)

            // Assert
            verify(exactly = 1) { message.send() }
        }

        @Test
        fun `should save the message to a store`() {
            // Arrange
            val messageToSend = MessageDto("")
            val dbObject = PersistentMessage()
            val message: Message = mockk()
            every { message.send() }.answers { }
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
