package com.github.igorilin13.data.games.impl

import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.games.api.GamesRepository
import com.github.igorilin13.data.games.impl.remote.GamesRemoteDataSource
import com.github.igorilin13.data.games.impl.remote.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

internal class GamesRepositoryImpl @Inject constructor(
    private val remoteSource: GamesRemoteDataSource
) : GamesRepository {
    override fun getGames(teamId: Int): Flow<Result<List<Game>>> {
        return flow {
            remoteSource.getTeamGames(teamId)
                .map { games -> games.map { it.toModel() } }
                .also { emit(it) }
        }
    }

    override fun getLeagueGames(date: LocalDate): Flow<Result<List<Game>>> {
        return flow {
            remoteSource.getLeagueGames(date)
                .map { games -> games.map { it.toModel() } }
                .also { emit(it) }
        }
    }
}