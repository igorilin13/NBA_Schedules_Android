package com.github.igorilin13.feature.settings.impl.settings

import androidx.lifecycle.viewModelScope
import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.settings.impl.settings.state.FavoriteTeamSettingState
import com.github.igorilin13.feature.settings.impl.settings.state.SettingsState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val teamsRepository: TeamsRepository
) : BaseViewModel<SettingsState, NoOpUiEvent>() {

    override fun createDefaultState(): SettingsState {
        return SettingsState(
            shouldHideScores = null,
            favoriteTeamState = FavoriteTeamSettingState.LoadingInfo
        )
    }

    override fun createUiStateFlow(): Flow<SettingsState> {
        val favoriteTeamState = settingsRepository.getFavoriteTeamId().flatMapLatest { teamId ->
            if (teamId != null) {
                teamsRepository.getTeam(teamId).map { result ->
                    result.fold(
                        onSuccess = { FavoriteTeamSettingState.HasFavorite(it) },
                        onFailure = { FavoriteTeamSettingState.Error }
                    )
                }.onStart { emit(FavoriteTeamSettingState.LoadingInfo) }
            } else {
                flowOf(FavoriteTeamSettingState.NoFavorite)
            }
        }

        return combine(
            settingsRepository.shouldHideScores(),
            favoriteTeamState
        ) { hideScores, favoriteTeam ->
            SettingsState(shouldHideScores = hideScores, favoriteTeamState = favoriteTeam)
        }
    }

    fun setHideScores(value: Boolean) {
        viewModelScope.launch {
            settingsRepository.setHideScores(value)
        }
    }

}