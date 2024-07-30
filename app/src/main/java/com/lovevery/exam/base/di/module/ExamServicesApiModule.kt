package com.lovevery.exam.base.di.module

import com.lovevery.exam.data.service.MessagesService
import com.lovevery.exam.base.di.network.Microservices
import com.lovevery.exam.base.mapper.BaseMapper
import com.lovevery.exam.data.mapper.SendMessageMapper
import com.lovevery.exam.data.model.MessageByUserResponse
import com.lovevery.exam.flow.model.MessageByUserUi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface ExamServicesApiModule {

    @Binds
    fun bindsSendMessageMapper(
        sendMessageMapper: SendMessageMapper
    ): BaseMapper<MessageByUserResponse, MessageByUserUi>

    companion object {

        @Provides
        fun providesMessagesService(
            @Microservices retrofit: Retrofit
        ): MessagesService {
            return retrofit.create(MessagesService::class.java)
        }
    }
}