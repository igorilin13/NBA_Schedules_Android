package com.github.igorilin13.data.games.impl.remote

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import javax.inject.Inject

internal class GamesRemoteDataSource @Inject constructor(
    private val service: GamesRemoteService
) {
    private val currentSeason by lazy { calculateCurrentApiSeason() }
    private val dateFormat by lazy { DateTimeFormatter.ofPattern("yyyy-MM-dd") }

    suspend fun getTeamGames(teamId: Int): Result<List<GameResponse>> {
        return runCatching {
            val result = mutableListOf<GameResponse>()
            var currentPage: Int? = 1

            while (currentPage != null) {
                val response =
                    service.getTeamGames(listOf(teamId), listOf(currentSeason), currentPage)
                result.addAll(response.data)
                currentPage = response.meta.nextPage
            }

            result.sortedBy { it.date }
        }
    }

    suspend fun getLeagueGames(date: LocalDate): Result<List<GameResponse>> {
        return runCatching {
            service.getLeagueGames(listOf(date.format(dateFormat))).data
        }
    }

    private fun calculateCurrentApiSeason(): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        return if (currentMonth >= Calendar.AUGUST) {
            currentYear
        } else {
            currentYear - 1
        }
    }
}