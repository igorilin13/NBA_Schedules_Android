package com.github.igorilin13.data.settings.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val onboardingCompletedKey = booleanPreferencesKey("onboarding_completed")

    suspend fun isOnboardingComplete(): Boolean {
        return dataStore.data.map { it[onboardingCompletedKey] ?: false }.first()
    }

    suspend fun setOnboardingComplete(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[onboardingCompletedKey] = value
        }
    }
}