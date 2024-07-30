package com.lovevery.exam.data.repository

import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.data.model.MessageSentResponse
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
    private val mapperList: BaseMapper<MessageByUserResponse, MessageByUserUi>,
    private val mapperItem: BaseMapper<MessageSentResponse, MessageByUserUi>
) {

    fun getMessageByUser(
        userName: String
    ): Single<MessageByUserUi> {
        return sendMessageRepository.getMessagesByName(userName).map {
            mapperList.map(it)
        }.applySchedulers()
    }

    fun sendNewMessage(
        message: MessageByUserRequest
    ): Single<MessageByUserUi> {
        return sendMessageRepository.sendMessage(message).map {
            mapperItem.map(it)
        }.applySchedulers()
    }
}