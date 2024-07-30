package com.lovevery.exam.flow.model

data class MessageByUserUi(
    val user: String,
    val listMessage: List<BodyMessage>
) {
    data class BodyMessage(
        val subject: String,
        val message: String
    )
}