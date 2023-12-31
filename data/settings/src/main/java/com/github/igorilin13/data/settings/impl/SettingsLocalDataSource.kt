package com.github.igorilin13.data.settings.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val onboardingCompletedKey = booleanPreferencesKey("onboarding_completed")
    private val favoriteTeamKey = intPreferencesKey("favorite_team_id")
    private val hideScoresKey = booleanPreferencesKey("hide_scores")

    suspend fun isOnboardingComplete(): Boolean {
        return dataStore.data.map { it[onboardingCompletedKey] ?: false }.first()
    }

    suspend fun setOnboardingComplete(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[onboardingCompletedKey] = value
        }
    }

    suspend fun setFavoriteTeam(id: Int) {
        dataStore.edit { preferences ->
            preferences[favoriteTeamKey] = id
        }
    }

    fun getFavoriteTeamId(): Flow<Int?> {
        return dataStore.data.map { it[favoriteTeamKey] }.distinctUntilChanged()
    }

    suspend fun setHideScores(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[hideScoresKey] = value
        }
    }

    fun shouldHideScores(): Flow<Boolean?> {
        return dataStore.data.map { it[hideScoresKey] }.distinctUntilChanged()
    }
}