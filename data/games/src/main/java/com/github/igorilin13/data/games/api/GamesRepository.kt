package com.github.igorilin13.data.games.api

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GamesRepository {
    fun getGames(teamId: Int): Flow<Result<List<Game>>>

    fun getLeagueGames(date: LocalDate): Flow<Result<List<Game>>>
}