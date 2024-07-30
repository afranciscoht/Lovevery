package com.lovevery.exam.data.repository

import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.data.model.MessageSentResponse
import com.lovevery.exam.data.service.MessagesService
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.utils.extensions.applySchedulers
import io.reactivex.Single
import javax.inject.Inject

class SendMessageRepositoryImpl @Inject constructor(
    private val messageRepository: MessagesService,
    private val mapperList: BaseMapper<MessageByUserResponse, MessageByUserUi>,
    private val mapperItem: BaseMapper<MessageSentResponse, MessageByUserUi>
) : SendMessageRepository {

    override fun getMessageByUser(
        userName: String
    ): Single<MessageByUserUi> {
        return messageRepository.getMessagesByName(userName).map {
            mapperList.map(it)
        }.applySchedulers()
    }

    override fun sendNewMessage(
        message: MessageByUserRequest
    ): Single<MessageByUserUi> {
        return messageRepository.sendMessage(message).map {
            mapperItem.map(it)
        }.applySchedulers()
    }
}