package com.github.igorilin13.domain.game

import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.domain.date.FormatGameDateUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

abstract class BaseGetGamesUseCase(
    private val formatGameDateUseCase: FormatGameDateUseCase
) {

    protected fun createResultFlow(
        games: Flow<Result<List<Game>>>,
        shouldHideScores: Flow<Boolean>
    ): Flow<Result<List<GameItem>>> {
        return combine(games, shouldHideScores) { gamesResult, hideScores ->
            gamesResult.map { games ->
                games.map { it.toGameItem(hideScores) }
            }
        }
    }

    private fun Game.toGameItem(hideScores: Boolean): GameItem {
        return GameItem(
            model = this,
            formattedDate = formatGameDateUseCase(date),
            showScores = !hideScores
        )
    }
}