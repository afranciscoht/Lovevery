package com.lovevery.exam.data.model

import com.google.gson.annotations.SerializedName

data class MessageByUserRequest(
    @SerializedName("user") val user: String,
    @SerializedName("operation") val operation: String? = "add_message",
    @SerializedName("subject") val subject: String,
    @SerializedName("message") val message: String
)