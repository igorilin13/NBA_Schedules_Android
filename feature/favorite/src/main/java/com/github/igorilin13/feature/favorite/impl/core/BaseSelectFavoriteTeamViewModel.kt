package com.github.igorilin13.feature.favorite.impl.core

import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamState
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamUiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

internal abstract class BaseSelectFavoriteTeamViewModel(
    private val settingsRepository: SettingsRepository,
    private val teamsRepository: TeamsRepository
) : BaseViewModel<SelectFavoriteTeamState, SelectFavoriteTeamUiEvent>() {
    private val selectedTeamId = MutableStateFlow<Int?>(null)

    override fun createDefaultState(): SelectFavoriteTeamState {
        return SelectFavoriteTeamState.Loading
    }

    override fun createUiStateFlow(): Flow<SelectFavoriteTeamState> {
        return combine(teamsRepository.getTeams(), selectedTeamId) { teamsResult, selectedId ->
            teamsResult.fold(
                onSuccess = { teams ->
                    SelectFavoriteTeamState.Display(teams, selectedId)
                },
                onFailure = {
                    SelectFavoriteTeamState.Error
                }
            )
        }
    }

    fun selectTeam(team: Team) {
        selectedTeamId.value = team.id
    }

    abstract fun confirmSelection()

    protected suspend fun saveSelectedTeam() {
        selectedTeamId.value?.let { id ->
            settingsRepository.setFavoriteTeam(id)
        }
    }
}