package com.lovevery.exam.data.service

import com.lovevery.exam.data.model.MessageByUserRequest
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.data.model.MessageSentResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MessagesService {

    @GET("messages/{user}")
    fun getMessagesByName(
        @Path("user") userName: String
    ): Single<MessageByUserResponse>

    @POST("messages")
    fun sendMessage(
        @Body message: MessageByUserRequest
    ): Single<MessageSentResponse>
}
