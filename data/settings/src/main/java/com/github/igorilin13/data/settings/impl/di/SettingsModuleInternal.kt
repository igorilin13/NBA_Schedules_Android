package com.github.igorilin13.data.settings.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.settings.impl.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface SettingsModuleInternal {
    @Binds
    fun settingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    companion object {

        @Singleton
        @Provides
        fun dataStore(context: Context): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create {
                context.preferencesDataStoreFile(name = "settings")
            }
        }
    }
}