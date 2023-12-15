package com.github.igorilin13.data.teams.api

import kotlinx.coroutines.flow.Flow

interface TeamsRepository {
    fun getTeams(): Flow<Result<List<Team>>>

    fun getTeam(id: Int): Flow<Result<Team>>
}