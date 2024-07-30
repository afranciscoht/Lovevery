package com.lovevery.exam.flow.action

import com.lovevery.exam.base.views.snack.SnackbarState
import com.lovevery.exam.flow.model.MessageByUserUi

sealed interface SendMessageAction {
    data class ShowLoading(
        val showLoading: Boolean
    ) : SendMessageAction

    data class ShowListMessageByUser(
        val listMessage: List<MessageByUserUi.BodyMessage>
    ) : SendMessageAction

    data class ShowMessage(
        val message: String,
        val state: SnackbarState
    ) : SendMessageAction
}