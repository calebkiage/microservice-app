package com.example.microservice.messaging.domain.entities

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@DisplayName("A message")
class MessageTests {
    private var message: Message? = null

    @BeforeEach
    fun beforeEach() {
        val clock = Clock.fixed(Instant.parse("2020-04-05T05:57:51.00Z"), ZoneId.systemDefault())
        this.message = Message("Test message", clock)
    }

    @Nested
    @DisplayName("on construction")
    inner class CreateMessageTests {
        @Test
        fun `should reject a message with blank content`() {
            // Arrange + Act + Assert
            Assertions.assertThrows(IllegalArgumentException::class.java) {
                Message("")
            }
        }

        @ParameterizedTest(name = "should trim start and end spaces [{index}]\n{argumentsWithNames}")
        @ValueSource(strings = [" test", "test ", "\n\ntest"])
        fun `should trim start and end spaces`(testString: String) {
            // Arrange + Act
            val message = Message(testString)

            // Assert
            Assertions.assertEquals("test", message.content)
        }
    }

    @Nested
    @DisplayName("when edit")
    inner class EditMessageTests {
        @Test
        fun `should update a message's content`() {
            // Arrange
            Assertions.assertNotNull(message)
            val message: Message = message!!

            // Act
            message.editContent("Test message edited")

            // Assert
            Assertions.assertEquals("Test message edited", message.content)
        }

        @Test
        fun `should reject empty message`() {
            // Arrange
            Assertions.assertNotNull(message)
            val message = message!!

            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException::class.java) {
                message.editContent("")
            }
        }

        @Test
        fun `should reject blank message`() {
            // Arrange
            Assertions.assertNotNull(message)
            val message = message!!

            // Act + Assert
            Assertions.assertThrows(IllegalArgumentException::class.java) {
                message.editContent(" ")
            }
        }

        @ParameterizedTest(name = "should trim start and end spaces [{index}]\n{argumentsWithNames}")
        @ValueSource(strings = [" test", "test ", "\n\ntest"])
        fun `should trim start and end spaces`(testString: String) {
            // Arrange
            Assertions.assertNotNull(message)
            val message = message!!

            // Act
            message.editContent(testString)

            // Assert
            Assertions.assertEquals("test", message.content)
        }
    }

    @Nested
    @DisplayName("when send")
    inner class SendMessageTests {
        @Test
        fun `should update a message's sent timestamp`() {
            // Arrange
            Assertions.assertNotNull(message)
            val message: Message = message!!

            // Act
            message.send()

            // Assert
            Assertions.assertNotNull(message.sentOn)
            val instant = Instant.parse("2020-04-05T05:57:51.00Z")
            Assertions.assertEquals(instant.toEpochMilli(), message.sentOn?.toEpochMilli())
        }

        @Test
        fun `should not send a message more than once`() {
            // Arrange
            Assertions.assertNotNull(message)
            val message = message!!

            // Act + Assert exception
            message.send()

            Assertions.assertThrows(IllegalStateException::class.java) {
                message.send()
            }
        }
    }
}
