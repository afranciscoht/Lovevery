package com.lovevery.exam.data.mapper

import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.utils.extensions.assertThrowsIllegalArgumentExceptionWithMessage
import org.junit.Assert
import org.junit.Test

class GetMessagesMapperTest {

    private val mapper = GetMessagesMapper()

    private val response = MessageByUserResponse(
        statusCode = 200,
        body = "{\"user\": \"Paco\", \"message\": [{\"subject\": \"Exam\", \"message\": \"Coding\"}]}"
    )

    private val bodyWithLessSubject =
        "{\"user\": \"Paco\", \"message\": [{ \"message\": \"Coding\"}]}"

    private val bodyWithLessBody = "{\"user\": \"Paco\", \"message\": [{\"subject\": \"Exam\"}]}"

    private val bodyWithLessUser = "{\"message\": [{\"subject\": \"Exam\", \"message\": \"Coding\"}]}"

    private val uiResponse = MessageByUserUi(
        user = "Paco",
        listMessage = listOf(
            MessageByUserUi.BodyMessage(
                subject = "Exam",
                message = "Coding"
            )
        )
    )

    @Test
    fun `Mapping response should return MessageByUserUi successfully when response has all fields`() {
        val mappedResponse = mapper.map(response)
        Assert.assertEquals(uiResponse, mappedResponse)
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
            mapper.map(response.copy(body = bodyWithLessBody))
        }
    }

    @Test
    fun `Mapping response should throw an exception when user is null`() {
        assertThrowsIllegalArgumentExceptionWithMessage(
            "Body.User is required"
        ) {
            mapper.map(response.copy(body = bodyWithLessUser))
        }
    }
}