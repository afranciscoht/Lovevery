package com.lovevery.exam.data.mapper

import com.google.gson.Gson
import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.base.mapper.requireNotNullOrBlank
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import javax.inject.Inject

class GetMessagesMapper @Inject constructor() :
    BaseMapper<MessageByUserResponse, MessageByUserUi> {

    override fun map(input: MessageByUserResponse): MessageByUserUi {
        val gson = Gson()
        try {
            val bodyResponse = gson.fromJson(input.body, MessageByUserResponse.Body::class.java)
            return MessageByUserUi(
                user = bodyResponse.user.requireNotNullOrBlank("Body.User"),
                listMessage = mapListMessage(bodyResponse.listMessage)
            )
        } catch (exception: IllegalArgumentException) {
            throw exception
        }
    }

    private fun mapListMessage(listMessage: List<MessageByUserResponse.Message>?): List<MessageByUserUi.BodyMessage> {
        return listMessage?.let {
            it.map { item ->
                MessageByUserUi.BodyMessage(
                    subject = item.subject.requireNotNullOrBlank("Message.Subject"),
                    message = item.message.requireNotNullOrBlank("Message.Body")
                )
            }
        } ?: emptyList()
    }
}