package com.github.igorilin13.data.settings.impl

import com.github.igorilin13.data.settings.api.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val localDataSource: SettingsLocalDataSource
) : SettingsRepository {

    override suspend fun isOnboardingComplete(): Boolean {
        return localDataSource.isOnboardingComplete()
    }

    override suspend fun setOnboardingComplete() {
        localDataSource.setOnboardingComplete(true)
    }

    override suspend fun setFavoriteTeam(id: Int) {
        localDataSource.setFavoriteTeam(id)
    }

    override fun getFavoriteTeamId(): Flow<Int?> {
        return localDataSource.getFavoriteTeamId()
    }

    override fun shouldHideScores(): Flow<Boolean> {
        return localDataSource.shouldHideScores().map { it ?: false }
    }

    override suspend fun setHideScores(value: Boolean) {
        localDataSource.setHideScores(value)
    }
}