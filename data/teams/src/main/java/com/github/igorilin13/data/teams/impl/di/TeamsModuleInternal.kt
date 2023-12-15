package com.github.igorilin13.data.teams.impl.di

import android.content.Context
import androidx.room.Room
import com.github.igorilin13.common.network.createNetworkService
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.data.teams.impl.TeamsRepositoryImpl
import com.github.igorilin13.data.teams.impl.local.TeamsDatabase
import com.github.igorilin13.data.teams.impl.remote.TeamsRemoteService
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
internal interface TeamsModuleInternal {
    @Binds
    fun teamsRepository(impl: TeamsRepositoryImpl): TeamsRepository

    companion object {
        @Provides
        fun teamsRemoteService(okHttpClient: OkHttpClient, moshi: Moshi, baseUrl: String) =
            createNetworkService<TeamsRemoteService>(okHttpClient, moshi, baseUrl)

        @Singleton
        @Provides
        fun provideDb(context: Context): TeamsDatabase {
            return Room.databaseBuilder(
                context,
                TeamsDatabase::class.java,
                TeamsDatabase.DB_NAME
            ).build()
        }

        @Provides
        fun teamsDao(db: TeamsDatabase) = db.teamsDao()
    }
}