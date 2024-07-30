package com.lovevery.exam.data.model

import com.google.gson.annotations.SerializedName

data class MessageByUserResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("body") val body: Body?

) {
    data class Body(
        @SerializedName("user") val user: String?,
        @SerializedName("message") val listMessage: List<Message>?
    )

    data class Message(
        @SerializedName("subject") val subject: String?,
        @SerializedName("message") val message: String?
    )
}