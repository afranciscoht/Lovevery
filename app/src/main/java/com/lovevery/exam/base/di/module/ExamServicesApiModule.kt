package com.lovevery.exam.base.di.module

import com.lovevery.exam.base.data.service.MessagesService
import com.lovevery.exam.base.di.network.Microservices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
interface ExamServicesApiModule {

    //add mapper

    companion object {

        @Provides
        fun providesMessagesService(
            @Microservices retrofit: Retrofit
        ): MessagesService {
            return retrofit.create(MessagesService::class.java)
        }
    }
}