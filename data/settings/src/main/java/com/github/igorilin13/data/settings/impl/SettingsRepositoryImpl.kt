package com.github.igorilin13.data.settings.impl

import com.github.igorilin13.data.settings.api.SettingsRepository
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

}