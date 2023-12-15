package com.github.igorilin13.league.games.impl.games.state

import com.github.igorilin13.domain.game.GameItem

internal sealed interface LeagueGamesState {
    data object Loading : LeagueGamesState
    data object Error : LeagueGamesState
    data object NoGamesAvailable : LeagueGamesState
    data class DisplayData(val games: List<GameItem>) : LeagueGamesState
}
