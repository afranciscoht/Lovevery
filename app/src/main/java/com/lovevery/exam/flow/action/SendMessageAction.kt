package com.lovevery.exam.flow.action

sealed interface SendMessageAction {
    data class ShowLoading(
        val showLoading: Boolean
    ) : SendMessageAction
}