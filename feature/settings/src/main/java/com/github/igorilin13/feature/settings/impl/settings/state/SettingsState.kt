package com.github.igorilin13.feature.settings.impl.settings.state

import com.github.igorilin13.data.teams.api.Team

internal data class SettingsState(
    val shouldHideScores: Boolean?,
    val favoriteTeamState: FavoriteTeamSettingState
)

internal sealed interface FavoriteTeamSettingState {
    data object LoadingInfo : FavoriteTeamSettingState
    data object Error : FavoriteTeamSettingState
    data object NoFavorite : FavoriteTeamSettingState
    data class HasFavorite(val team: Team) : FavoriteTeamSettingState
}