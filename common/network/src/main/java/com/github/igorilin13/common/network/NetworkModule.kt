package com.github.igorilin13.common.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun chuckerInterceptor(context: Context) = ChuckerInterceptor(context)

    @Singleton
    @Provides
    fun okHttp(chuckerInterceptor: ChuckerInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(chuckerInterceptor).build()

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().build()
}