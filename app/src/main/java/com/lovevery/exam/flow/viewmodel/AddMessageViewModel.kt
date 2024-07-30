package com.lovevery.exam.flow.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lovevery.exam.R
import com.lovevery.exam.base.di.providers.ResourceProvider
import com.lovevery.exam.base.viewmodel.BaseViewModel
import com.lovevery.exam.base.viewmodel.SingleLiveEvent
import com.lovevery.exam.base.views.snack.SnackbarState
import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.data.repository.SendMessageRepository
import com.lovevery.exam.flow.action.SendMessageAction
import com.lovevery.exam.flow.model.MessageByUserUi
import com.lovevery.exam.utils.extensions.asLiveData
import com.lovevery.exam.utils.extensions.asValue
import javax.inject.Inject

class AddMessageViewModel @Inject constructor(
    private val repository: SendMessageRepository,
    private val resourceRequest: ResourceProvider
) : BaseViewModel() {

    private val subjectMessageValue = MutableLiveData("")
    private val bodyMessageValue = MutableLiveData("")

    private val showProgress = SingleLiveEvent<Boolean>()
    fun getShowProgress() = showProgress.asLiveData()

    private val action = SingleLiveEvent<SendMessageAction>()
    fun getAction() = action.asLiveData()

    private val userNameValue = MutableLiveData("")

    private val isButtonEnable = MediatorLiveData<Boolean>().apply {
        addSource(subjectMessageValue) { value = isMessageComplete() }
        addSource(bodyMessageValue) { value = isMessageComplete() }
    }

    private val listMessage = mutableListOf<MessageByUserUi.BodyMessage>()

    fun getButtonEnable() = isButtonEnable.asLiveData()

    fun setUserNameValue(userName: String) {
        userNameValue.value = userName
    }

    fun setSubjectValue(subject: String) {
        subjectMessageValue.value = subject
    }

    fun setBodyValue(body: String) {
        bodyMessageValue.value = body
    }

    private fun isMessageComplete() =
        subjectMessageValue.asValue().isNotEmpty() && bodyMessageValue.asValue().isNotEmpty()

    fun sendNewMessage(
        subject: String,
        body: String
    ) {
        val newMessageByUserRequest = MessageByUserRequest(
            user = userNameValue.asValue(),
            subject = subject,
            message = body
        )
        disposable.add(
            repository.sendNewMessage(newMessageByUserRequest)
                .doOnSubscribe { showProgress.value = true }
                .doFinally { showProgress.value = false }
                .subscribe({

                    listMessage.add(it.listMessage.first())

                    action.value = if (it.listMessage.isNotEmpty()) {
                        SendMessageAction.ShowListMessageByUser(listMessage)
                    } else {
                        SendMessageAction.ShowMessage(
                            resourceRequest.getString(R.string.success_message_empty_list),
                            SnackbarState.SUCCESS
                        )
                    }
                }, {
                    action.value =
                        SendMessageAction.ShowMessage(
                            resourceRequest.getString(R.string.generic_error),
                            SnackbarState.ERROR
                        )
                })
        )
    }

    fun getMessageByName() {
        disposable.add(
            repository.getMessageByUser(userNameValue.asValue())
                .doOnSubscribe { action.value = SendMessageAction.ShowLoading(true) }
                .doFinally { action.value = SendMessageAction.ShowLoading(false) }
                .subscribe({
                    action.value = if (it.listMessage.isNotEmpty()) {
                        SendMessageAction.ShowListMessageByUser(it.listMessage)
                    } else {
                        SendMessageAction.ShowMessage(
                            resourceRequest.getString(R.string.success_message_empty_list),
                            SnackbarState.SUCCESS
                        )
                    }
                }, {
                    action.value =
                        SendMessageAction.ShowMessage(
                            resourceRequest.getString(R.string.generic_error),
                            SnackbarState.ERROR
                        )
                })
        )
    }
}