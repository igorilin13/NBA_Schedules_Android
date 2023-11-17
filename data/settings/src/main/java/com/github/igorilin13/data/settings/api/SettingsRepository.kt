package com.github.igorilin13.data.settings.api

interface SettingsRepository {
    suspend fun isOnboardingComplete(): Boolean

    suspend fun setOnboardingComplete()
}
