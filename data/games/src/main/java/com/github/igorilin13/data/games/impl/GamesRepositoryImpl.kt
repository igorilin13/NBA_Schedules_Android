package com.github.igorilin13.data.games.impl

import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.games.impl.remote.GamesRemoteDataSource
import com.github.igorilin13.data.games.impl.remote.toModel
import java.time.LocalDate
import javax.inject.Inject

internal class GamesRepositoryImpl @Inject constructor(
    private val remoteSource: GamesRemoteDataSource
): GamesRepository {
    override suspend fun getGames(teamId: Int): Result<List<Game>> {
        return remoteSource.getTeamGames(teamId).map { games ->
            games.map { it.toModel() }
        }
    }

    override suspend fun getLeagueGames(date: LocalDate): Result<List<Game>> {
        return remoteSource.getLeagueGames(date).map { games ->
            games.map { it.toModel() }
        }
    }
}