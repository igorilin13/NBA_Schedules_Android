package com.github.igorilin13.common.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

inline fun <reified T> createNetworkService(
    okHttpClient: OkHttpClient,
    moshi: Moshi,
    baseUrl: String
) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .build()
    .create<T>()