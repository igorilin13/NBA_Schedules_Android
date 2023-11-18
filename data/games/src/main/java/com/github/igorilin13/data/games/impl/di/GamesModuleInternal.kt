package com.github.igorilin13.data.games.impl.di

import com.github.igorilin13.common.network.createNetworkService
import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.games.impl.GamesRepositoryImpl
import com.github.igorilin13.data.games.impl.remote.GamesRemoteService
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
internal interface GamesModuleInternal {
    @Binds
    fun repository(impl: GamesRepositoryImpl): GamesRepository

    companion object {
        @Provides
        fun service(okHttpClient: OkHttpClient, moshi: Moshi, baseUrl: String) =
            createNetworkService<GamesRemoteService>(okHttpClient, moshi, baseUrl)
    }
}