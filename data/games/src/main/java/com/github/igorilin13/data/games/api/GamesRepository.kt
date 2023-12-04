package com.github.igorilin13.data.games.api

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GamesRepository {
    suspend fun getGames(teamId: Int): Flow<Result<List<Game>>>

    suspend fun getLeagueGames(date: LocalDate): Flow<Result<List<Game>>>
}