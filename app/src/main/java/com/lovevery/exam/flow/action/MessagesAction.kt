package com.lovevery.exam.flow.action

sealed interface MessagesAction {

    data class ShowUserList(
        val listUsers: List<String>
    ) : MessagesAction
}