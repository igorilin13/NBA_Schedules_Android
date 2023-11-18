package com.github.igorilin13.data.games.api

import java.time.LocalDate

interface GamesRepository {
    suspend fun getGames(teamId: Int): Result<List<Game>>

    suspend fun getLeagueGames(date: LocalDate): Result<List<Game>>
}