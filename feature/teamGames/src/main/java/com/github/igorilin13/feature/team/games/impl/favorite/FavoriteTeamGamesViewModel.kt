package com.github.igorilin13.feature.team.games.impl.favorite

import com.github.igorilin13.common.ui.screen.BaseViewModel
import com.github.igorilin13.common.ui.screen.NoOpUiEvent
import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.games.api.GameStatus
import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.domain.FormatGameDateUseCase
import com.github.igorilin13.feature.team.games.impl.favorite.state.FavoriteTeamGamesState
import com.github.igorilin13.feature.team.games.impl.favorite.state.GameItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class FavoriteTeamGamesViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val gamesRepository: GamesRepository,
    private val formatGameDateUseCase: FormatGameDateUseCase
) : BaseViewModel<FavoriteTeamGamesState, NoOpUiEvent>() {

    override fun createDefaultState(): FavoriteTeamGamesState {
        return FavoriteTeamGamesState.Loading
    }

    override fun createUiStateFlow(): Flow<FavoriteTeamGamesState> {
        return settingsRepository.getFavoriteTeamId().flatMapLatest { teamId ->
            if (teamId != null) {
                combine(
                    gamesRepository.getGames(teamId),
                    settingsRepository.shouldHideScores()
                ) { gamesResult, hideScores ->
                    gamesResult.fold(
                        onSuccess = { games ->
                            if (games.isNotEmpty()) {
                                createDisplayState(games, hideScores)
                            } else {
                                FavoriteTeamGamesState.NoGamesAvailable
                            }
                        },
                        onFailure = { FavoriteTeamGamesState.Error }
                    )
                }
            } else {
                flowOf(FavoriteTeamGamesState.NoFavoriteTeam)
            }
        }
    }

    private fun createDisplayState(
        games: List<Game>,
        hideScores: Boolean
    ): FavoriteTeamGamesState.DisplayData {
        val pastGames = games
            .filter { it.gameStatus == GameStatus.FINISHED }
            .sortedByDescending { it.date }
        val upcomingGames = games.filter { it.gameStatus != GameStatus.FINISHED }

        return FavoriteTeamGamesState.DisplayData(
            nextGame = upcomingGames.firstOrNull()?.toUiModel(hideScores),
            previousGame = pastGames.firstOrNull()?.toUiModel(hideScores),
            upcomingGames = upcomingGames.take(MAX_UPCOMING_GAMES).map { it.toUiModel(hideScores) },
            previousGames = pastGames.map { it.toUiModel(hideScores) }
        )
    }

    private fun Game.toUiModel(hideScores: Boolean): GameItem {
        return GameItem(
            model = this,
            formattedDate = formatGameDateUseCase(date),
            showScores = !hideScores
        )
    }

    private companion object {
        private const val MAX_UPCOMING_GAMES = 5
    }
}