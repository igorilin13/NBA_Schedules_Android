package com.github.igorilin13.data.teams.impl

import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.data.teams.impl.remote.TeamsRemoteDataSource
import com.github.igorilin13.data.teams.impl.remote.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class TeamsRepositoryImpl @Inject constructor(
    private val remoteDataSource: TeamsRemoteDataSource
) : TeamsRepository {
    override fun getTeams(): Flow<Result<List<Team>>> {
        return flow {
            emit(
                remoteDataSource
                    .getTeams()
                    .map { teams -> teams.map { it.toModel() } }
            )
        }
    }
}