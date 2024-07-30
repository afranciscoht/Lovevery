package com.lovevery.exam.flow.viewmodel

import com.lovevery.exam.base.viewmodel.BaseViewModel
import com.lovevery.exam.data.repository.SendMessageRepository
import javax.inject.Inject

class AddMessageViewModel @Inject constructor(
    private val repository: SendMessageRepository
) : BaseViewModel() {


    fun sendNewMessage() {

    }

}