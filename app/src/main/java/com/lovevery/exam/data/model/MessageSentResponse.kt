package com.lovevery.exam.data.model

import com.google.gson.annotations.SerializedName

data class MessageSentResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("body") val body: String?
) {
    data class Body(
        @SerializedName("user") val user: String?,
        @SerializedName("subject") val subject: String?,
        @SerializedName("message") val  message: String?
    )
}