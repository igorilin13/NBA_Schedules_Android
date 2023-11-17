package com.github.igorilin13.data.teams.impl.remote

import javax.inject.Inject

internal class TeamsRemoteDataSource @Inject constructor(
    private val service: TeamsRemoteService
) {
    private val activeTeamIds = (1..30).toSet()

    suspend fun getTeams() = runCatching {
        service.getTeams().data.filter { activeTeamIds.contains(it.id) }
    }
}