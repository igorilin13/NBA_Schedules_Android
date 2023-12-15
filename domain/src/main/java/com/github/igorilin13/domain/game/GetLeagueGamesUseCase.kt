package com.github.igorilin13.domain.game

import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.domain.date.FormatGameDateUseCase
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetLeagueGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val settingsRepository: SettingsRepository,
    formatGameDateUseCase: FormatGameDateUseCase
) : BaseGetGamesUseCase(formatGameDateUseCase) {
    operator fun invoke(date: LocalDate): Flow<Result<List<GameItem>>> {
        return createResultFlow(
            games = gamesRepository.getLeagueGames(date),
            shouldHideScores = settingsRepository.shouldHideScores()
        )
    }
}