package com.github.igorilin13.domain.game.favorite

import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.domain.date.FormatGameDateUseCase
import com.github.igorilin13.domain.game.BaseGetGamesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoriteTeamGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val settingsRepository: SettingsRepository,
    formatGameDateUseCase: FormatGameDateUseCase
) : BaseGetGamesUseCase(formatGameDateUseCase) {
    operator fun invoke(): Flow<FavoriteTeamGamesResult> {
        return settingsRepository.getFavoriteTeamId().flatMapLatest { teamId ->
            if (teamId != null) {
                createResultFlow(
                    games = gamesRepository.getGames(teamId),
                    shouldHideScores = settingsRepository.shouldHideScores()
                ).map { FavoriteTeamGamesResult.HasFavoriteTeam(it) }
            } else {
                flowOf(FavoriteTeamGamesResult.NoFavoriteTeam)
            }
        }
    }
}