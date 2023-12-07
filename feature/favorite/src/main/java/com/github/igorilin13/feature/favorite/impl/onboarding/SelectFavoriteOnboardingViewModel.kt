package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.lifecycle.viewModelScope
import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingState
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SelectFavoriteOnboardingViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val teamsRepository: TeamsRepository
) : BaseViewModel<SelectFavoriteOnboardingState, SelectFavoriteOnboardingUiEvent>() {
    private val selectedTeamId = MutableStateFlow<Int?>(null)

    override fun createDefaultState(): SelectFavoriteOnboardingState {
        return SelectFavoriteOnboardingState.Loading
    }

    override fun createUiStateFlow(): Flow<SelectFavoriteOnboardingState> {
        return combine(teamsRepository.getTeams(), selectedTeamId) { teamsResult, selectedId ->
            teamsResult.fold(
                onSuccess = { teams ->
                    SelectFavoriteOnboardingState.Display(teams, selectedId)
                },
                onFailure = {
                    SelectFavoriteOnboardingState.Error
                }
            )
        }
    }

    fun skipOnboarding() {
        completeOnboarding(saveSelected = false)
    }

    fun selectTeam(team: Team) {
        selectedTeamId.value = team.id
    }

    fun confirmSelection() {
        completeOnboarding(saveSelected = true)
    }

    private fun completeOnboarding(saveSelected: Boolean) {
        viewModelScope.launch {
            if (saveSelected) {
                selectedTeamId.value?.let { id ->
                    settingsRepository.saveFavoriteTeam(id)
                }
            }
            settingsRepository.setOnboardingComplete()
            _uiEvents.tryEmit(SelectFavoriteOnboardingUiEvent.OnboardingComplete)
        }
    }
}