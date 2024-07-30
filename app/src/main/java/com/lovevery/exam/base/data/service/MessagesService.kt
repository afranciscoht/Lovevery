package com.lovevery.exam.base.data.service

import com.lovevery.exam.base.data.model.MessageByUserRequest
import com.lovevery.exam.base.data.model.MessageByUserResponse
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
    ): Single<Completable>
}
