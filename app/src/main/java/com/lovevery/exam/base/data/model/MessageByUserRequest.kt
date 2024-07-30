package com.lovevery.exam.base.data.model

import com.google.gson.annotations.SerializedName

data class MessageByUserRequest(
    @SerializedName("user") val user: String,
    @SerializedName("operation") val operation: String,
    @SerializedName("subject") val subject: String,
    @SerializedName("message") val message: String
)