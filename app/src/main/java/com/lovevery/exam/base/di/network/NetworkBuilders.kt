package com.lovevery.exam.base.di.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitBuilder(
    httpClient: OkHttpClient,
    path: String
): Retrofit.Builder {
    return Retrofit.Builder()
        .client(httpClient)
        .baseUrl(path)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
}