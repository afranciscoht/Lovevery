package com.lovevery.exam.data.mapper

import com.google.gson.Gson
import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.base.mapper.requireNotNullOrBlank
import com.lovevery.exam.data.model.MessageSentResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import javax.inject.Inject

class SentMessageMapper @Inject constructor(
) : BaseMapper<MessageSentResponse, MessageByUserUi> {

    override fun map(input: MessageSentResponse): MessageByUserUi {
        val gson = Gson()
        try {
            val bodyResponse = gson.fromJson(input.body, MessageSentResponse.Body::class.java)
            return MessageByUserUi(
                user = bodyResponse.user.requireNotNullOrBlank("Body.User"),
                listMessage = mapListMessage(bodyResponse)
            )
        } catch (exception: IllegalArgumentException) {
            throw exception
        }
    }

    private fun mapListMessage(message: MessageSentResponse.Body?): List<MessageByUserUi.BodyMessage> {
        return listOf(
            MessageByUserUi.BodyMessage(
                subject = message?.subject.requireNotNullOrBlank("Message.Subject"),
                message = message?.message.requireNotNullOrBlank("Message.Body")
            )
        )
    }
}