package com.lovevery.exam.data.mapper

import com.lovevery.exam.data.model.MessageSentResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.utils.extensions.assertThrowsIllegalArgumentExceptionWithMessage
import org.junit.Assert
import org.junit.Test

class SentMessageMapperTest {

    private val mapper = SentMessageMapper()

    private val response = MessageSentResponse(
        statusCode = 200,
        body = "{\"user\": \"Paco\", \"subject\": \"Title mock\", \"message\": \"Message mock\"}"
    )

    private val bodyWithLessUser = "{\"subject\": \"Title mock\", \"message\": \"Message mock\"}"
    private val bodyWithLessSubject = "{\"user\": \"Paco\", \"message\": \"Message mock\"}"
    private val bodyWithLessMessage = "{\"user\": \"Paco\", \"subject\": \"Title mock\"}"

    private val uiResponse = MessageByUserUi(
        user = "Paco",
        listMessage = listOf(
            MessageByUserUi.BodyMessage(
                subject = "Title mock",
                message = "Message mock"
            )
        )
    )

    @Test
    fun `Mapping response should return MessageByUserUi successfully when response has all fields`() {
        val mappedResponse = mapper.map(response)
        Assert.assertEquals(uiResponse, mappedResponse)
    }

    @Test
    fun `Mapping response should throw an exception when user is null`() {
        assertThrowsIllegalArgumentExceptionWithMessage(
            "Body.User is required"
        ) {
            mapper.map(response.copy(body = bodyWithLessUser))
        }
    }

    @Test
    fun `Mapping response should throw an exception when subject is null`() {
        assertThrowsIllegalArgumentExceptionWithMessage(
            "Message.Subject is required"
        ) {
            mapper.map(response.copy(body = bodyWithLessSubject))
        }
    }

    @Test
    fun `Mapping response should throw an exception when message is null`() {
        assertThrowsIllegalArgumentExceptionWithMessage(
            "Message.Body is required"
        ) {
            mapper.map(response.copy(body = bodyWithLessMessage))
        }
    }
}