package com.github.igorilin13.data.teams.impl

import com.github.igorilin13.data.teams.api.Team
import com.github.igorilin13.data.teams.api.TeamsRepository
import com.github.igorilin13.data.teams.impl.remote.TeamsRemoteDataSource
import javax.inject.Inject

internal class TeamsRepositoryImpl @Inject constructor(
    private val remoteDataSource: TeamsRemoteDataSource
) : TeamsRepository {
    override suspend fun getTeams(): Result<List<Team>> {
        return remoteDataSource.getTeams().map { teams ->
            teams.map { it.toModel() }
        }
    }
}