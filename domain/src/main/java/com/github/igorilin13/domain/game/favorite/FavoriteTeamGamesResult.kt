package com.github.igorilin13.domain.game.favorite

import com.github.igorilin13.domain.game.GameItem

sealed interface FavoriteTeamGamesResult {
    data object NoFavoriteTeam : FavoriteTeamGamesResult
    data class HasFavoriteTeam(val games: Result<List<GameItem>>) : FavoriteTeamGamesResult
}