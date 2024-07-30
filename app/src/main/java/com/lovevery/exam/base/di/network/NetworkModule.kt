package com.lovevery.exam.base.di.network

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIME_OUT = 20L
private val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

@Module
object NetworkModule {

    @AuthorizationInterceptors
    @Provides
    @Singleton
    @JvmStatic
    fun providesAuthorizationInterceptors(): ArrayList<Interceptor> {
        return arrayListOf(AuthorizationInterceptor())
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesOkHttpTimeout(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesMicroServiceOkHttp(
        httpClientBuilder: OkHttpClient.Builder,
        @AuthorizationInterceptors interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        interceptors.forEach { httpClientBuilder.addInterceptor(it) }
        return httpClientBuilder.addInterceptor(loggingInterceptor).build()
    }
}