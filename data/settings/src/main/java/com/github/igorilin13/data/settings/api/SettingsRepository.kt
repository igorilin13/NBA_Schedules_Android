package com.github.igorilin13.data.settings.api

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun isOnboardingComplete(): Boolean

    suspend fun setOnboardingComplete()

    suspend fun setFavoriteTeam(id: Int)

    fun getFavoriteTeamId(): Flow<Int?>

    fun shouldHideScores(): Flow<Boolean>

    suspend fun setHideScores(value: Boolean)
}
