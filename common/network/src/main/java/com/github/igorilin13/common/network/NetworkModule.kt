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
    internal fun authInterceptor(@ApiKeyQualifier apiKey: String) = AuthInterceptor(apiKey)

    @Singleton
    @Provides
    internal fun okHttp(
        chuckerInterceptor: ChuckerInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().build()
}