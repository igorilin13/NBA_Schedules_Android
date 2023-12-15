package com.github.igorilin13.league.games.impl.games

import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.domain.game.GetLeagueGamesUseCase
import com.github.igorilin13.league.games.impl.games.state.LeagueGamesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

internal class LeagueGamesViewModel @Inject constructor(
    private val getLeagueGamesUseCase: GetLeagueGamesUseCase
) : BaseViewModel<LeagueGamesState, NoOpUiEvent>() {

    override fun createDefaultState(): LeagueGamesState {
        return LeagueGamesState.Loading
    }

    override fun createUiStateFlow(): Flow<LeagueGamesState> {
        return getLeagueGamesUseCase(LocalDate.now()).map { gamesResult ->
            gamesResult.fold(
                onSuccess = { games ->
                    if (games.isNotEmpty()) {
                        LeagueGamesState.DisplayData(games)
                    } else {
                        LeagueGamesState.NoGamesAvailable
                    }
                },
                onFailure = { LeagueGamesState.Error }
            )
        }
    }

}