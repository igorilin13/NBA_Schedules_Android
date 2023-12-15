package com.github.igorilin13.feature.favorite.impl.core.state

import com.github.igorilin13.data.teams.api.Team

internal sealed interface SelectFavoriteTeamState {
    data object Loading : SelectFavoriteTeamState
    data object Error : SelectFavoriteTeamState
    data class Display(
        val teams: List<Team>,
        val selectedId: Int?
    ) : SelectFavoriteTeamState
}