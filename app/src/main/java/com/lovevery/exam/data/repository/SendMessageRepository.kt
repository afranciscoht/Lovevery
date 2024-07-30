package com.lovevery.exam.data.repository

import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.flow.model.MessageByUserUi
import io.reactivex.Single

interface SendMessageRepository {

    fun getMessageByUser(
        userName: String
    ): Single<MessageByUserUi>

    fun sendNewMessage(
        message: MessageByUserRequest
    ): Single<MessageByUserUi>
}