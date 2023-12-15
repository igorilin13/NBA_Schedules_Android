package com.github.igorilin13.feature.favorite.impl.favorite

import androidx.lifecycle.viewModelScope
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.feature.favorite.impl.core.BaseSelectFavoriteTeamViewModel
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamUiEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ChangeFavoriteViewModel @Inject constructor(
    settingsRepository: SettingsRepository,
    teamsRepository: TeamsRepository
) : BaseSelectFavoriteTeamViewModel(settingsRepository, teamsRepository) {

    override fun confirmSelection() {
        viewModelScope.launch {
            saveSelectedTeam()
            _uiEvents.tryEmit(SelectFavoriteTeamUiEvent.SelectionComplete)
        }
    }
}