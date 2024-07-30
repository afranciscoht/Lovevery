package com.lovevery.exam.base.data.repository

import com.lovevery.exam.base.data.service.MessagesService
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SendMessageRepository @Inject constructor(
    private val sendMessageRepository: MessagesService
) {

    fun getMessageByUser(userName: String) {
        sendMessageRepository.getMessagesByName(userName)
    }
}