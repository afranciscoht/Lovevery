package com.lovevery.exam.base.di.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    // includes = [
    //     ExamServicesApiModule::class
    // ]
)
object ExamServicesModule {

    @Provides
    @Singleton
    @Microservices
    @JvmStatic
    fun microServicesHttpClient(
        httpClient: OkHttpClient
    ): Retrofit {
        return retrofitBuilder(
            httpClient,
            "https://abraxvasbh.execute-api.us-east-2.amazonaws.com/proto/messages/"
        ).build()
    }
}