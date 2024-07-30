package com.lovevery.exam.data.mapper

import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.base.mapper.requireNotNullOrBlank
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import javax.inject.Inject

class SendMessageMapper @Inject constructor() :
    BaseMapper<MessageByUserResponse, MessageByUserUi> {

    override fun map(input: MessageByUserResponse): MessageByUserUi {
        try {

            return MessageByUserUi(
                user = input.body?.user.requireNotNullOrBlank("Body.User is required"),
                listMessage = mapListMessage(input.body?.listMessage)
            )
        } catch (exception: IllegalArgumentException) {
            throw exception
        }
    }

    private fun mapListMessage(listMessage: List<MessageByUserResponse.Message>?): List<MessageByUserUi.BodyMessage> {
        return listMessage?.let {
            it.map { item ->
                MessageByUserUi.BodyMessage(
                    subject = item.subject.requireNotNullOrBlank("Message.Title is required"),
                    message = item.message.requireNotNullOrBlank("Message.Body is required")
                )
            }
        } ?: emptyList()
    }
}