package com.github.igorilin13.feature.team.games.impl.favorite.state

import com.github.igorilin13.domain.game.GameItem

internal sealed interface FavoriteTeamGamesState {
    data object Loading : FavoriteTeamGamesState
    data object NoFavoriteTeam : FavoriteTeamGamesState
    data object NoGamesAvailable : FavoriteTeamGamesState
    data object Error : FavoriteTeamGamesState

    data class DisplayData(
        val nextGame: GameItem?,
        val previousGame: GameItem?,
        val upcomingGames: List<GameItem>,
        val previousGames: List<GameItem>
    ) : FavoriteTeamGamesState
}
