package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.lifecycle.ViewModel
import com.github.igorilin13.data.settings.api.SettingsRepository
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

}