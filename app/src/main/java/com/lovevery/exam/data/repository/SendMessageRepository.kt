package com.lovevery.exam.data.repository

import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.data.service.MessagesService
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.utils.extensions.applySchedulers
import dagger.Reusable
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class SendMessageRepository @Inject constructor(
    private val sendMessageRepository: MessagesService,
    private val mapper: BaseMapper<MessageByUserResponse, MessageByUserUi>
) {

    fun getMessageByUser(
        userName: String
    ): Single<MessageByUserUi> {
        return sendMessageRepository.getMessagesByName(userName).map {
            mapper.map(it)
        }.applySchedulers()
    }

    fun sendNewMessage(
        message: MessageByUserRequest
    ): Single<Completable> {
        return sendMessageRepository.sendMessage(message).applySchedulers()
    }
}