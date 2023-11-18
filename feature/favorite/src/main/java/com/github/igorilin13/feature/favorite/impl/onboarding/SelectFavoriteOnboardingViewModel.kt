package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.igorilin13.common.util.eventFlow
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingState
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SelectFavoriteOnboardingViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val teamsRepository: TeamsRepository
) : ViewModel() {
    private val _selectedTeamId = MutableStateFlow<Int?>(null)

    private val _uiEvents = eventFlow<SelectFavoriteOnboardingUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    val uiState: StateFlow<SelectFavoriteOnboardingState> = createStateFlow().stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        SelectFavoriteOnboardingState.Loading
    )

    private fun createStateFlow(): Flow<SelectFavoriteOnboardingState> {
        val loadTeams = flow {
            emit(teamsRepository.getTeams())
        }

        return combine(loadTeams, _selectedTeamId) { teamsResult, selectedId ->
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
        _selectedTeamId.value = team.id
    }

    fun confirmSelection() {
        completeOnboarding(saveSelected = true)
    }

    private fun completeOnboarding(saveSelected: Boolean) {
        viewModelScope.launch {
            if (saveSelected) {
                _selectedTeamId.value?.let { id ->
                    settingsRepository.saveFavoriteTeam(id)
                }
            }
            settingsRepository.setOnboardingComplete()
            _uiEvents.tryEmit(SelectFavoriteOnboardingUiEvent.OnboardingComplete)
        }
    }
}