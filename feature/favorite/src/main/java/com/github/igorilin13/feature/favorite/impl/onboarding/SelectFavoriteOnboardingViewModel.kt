package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.lifecycle.viewModelScope
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.favorite.impl.core.BaseSelectFavoriteTeamViewModel
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamUiEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SelectFavoriteOnboardingViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    teamsRepository: TeamsRepository
) : BaseSelectFavoriteTeamViewModel(settingsRepository, teamsRepository) {

    fun skipOnboarding() {
        completeOnboarding(saveSelected = false)
    }

    override fun confirmSelection() {
        completeOnboarding(saveSelected = true)
    }

    private fun completeOnboarding(saveSelected: Boolean) {
        viewModelScope.launch {
            if (saveSelected) {
                saveSelectedTeam()
            }
            settingsRepository.setOnboardingComplete()
            _uiEvents.tryEmit(SelectFavoriteTeamUiEvent.SelectionComplete)
        }
    }
}