package com.github.igorilin13.feature.team.games.impl.favorite

import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.data.games.api.GameStatus
import com.github.igorilin13.domain.game.GameItem
import com.github.igorilin13.domain.game.favorite.FavoriteTeamGamesResult
import com.github.igorilin13.domain.game.favorite.GetFavoriteTeamGamesUseCase
import com.github.igorilin13.feature.team.games.impl.favorite.state.FavoriteTeamGamesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FavoriteTeamGamesViewModel @Inject constructor(
    private val getFavoriteTeamGamesUseCase: GetFavoriteTeamGamesUseCase
) : BaseViewModel<FavoriteTeamGamesState, NoOpUiEvent>() {

    override fun createDefaultState(): FavoriteTeamGamesState {
        return FavoriteTeamGamesState.Loading
    }

    override fun createUiStateFlow(): Flow<FavoriteTeamGamesState> {
        return getFavoriteTeamGamesUseCase().map { result ->
            when (result) {
                is FavoriteTeamGamesResult.HasFavoriteTeam -> {
                    result.games.fold(
                        onSuccess = { games ->
                            if (games.isNotEmpty()) {
                                createDisplayState(games)
                            } else {
                                FavoriteTeamGamesState.NoGamesAvailable
                            }
                        },
                        onFailure = { FavoriteTeamGamesState.Error }
                    )
                }

                FavoriteTeamGamesResult.NoFavoriteTeam -> FavoriteTeamGamesState.NoFavoriteTeam
            }
        }
    }

    private fun createDisplayState(
        games: List<GameItem>
    ): FavoriteTeamGamesState.DisplayData {
        val pastGames = games
            .filter { it.model.gameStatus == GameStatus.FINISHED }
            .sortedByDescending { it.model.date }
        val upcomingGames = games.filter { it.model.gameStatus != GameStatus.FINISHED }

        return FavoriteTeamGamesState.DisplayData(
            nextGame = upcomingGames.firstOrNull(),
            previousGame = pastGames.firstOrNull(),
            upcomingGames = upcomingGames.take(MAX_UPCOMING_GAMES),
            previousGames = pastGames
        )
    }

    private companion object {
        private const val MAX_UPCOMING_GAMES = 5
    }
}