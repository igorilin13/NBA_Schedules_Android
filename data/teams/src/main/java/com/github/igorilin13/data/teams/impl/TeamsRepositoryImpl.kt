package com.github.igorilin13.data.teams.impl

import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.data.teams.impl.local.TeamsDao
import com.github.igorilin13.data.teams.impl.local.toModel
import com.github.igorilin13.data.teams.impl.remote.TeamsRemoteDataSource
import com.github.igorilin13.data.teams.impl.remote.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TeamsRepositoryImpl @Inject constructor(
    private val remoteDataSource: TeamsRemoteDataSource,
    private val localDataSource: TeamsDao
) : TeamsRepository {

    private var lastRefreshResult: Result<List<Team>>? = null
    private val refreshMutex = Mutex()

    override fun getTeams(): Flow<Result<List<Team>>> {
        val fromLocal = localDataSource.getTeams().map { teams -> teams.map { it.toModel() } }
        val fromRemote = flow { emit(refreshIfNeeded()) }
        return combine(fromLocal, fromRemote) { local, remote ->
            if (local.isNotEmpty()) {
                Result.success(local)
            } else {
                remote
            }
        }
    }

    private suspend fun refreshIfNeeded(): Result<List<Team>> {
        return refreshMutex.withLock {
            val previous = lastRefreshResult
            if (previous != null && previous.isSuccess) {
                previous
            } else {
                remoteDataSource.getTeams()
                    .map { teams -> teams.map { it.toModel() } }
                    .onSuccess { localDataSource.replace(it) }
                    .also { lastRefreshResult = it }
            }
        }
    }

    override fun getTeam(id: Int): Flow<Result<Team>> {
        return getTeams().map { allTeamsResult ->
            allTeamsResult.map { teams -> teams.first { it.id == id } }
        }
    }
}