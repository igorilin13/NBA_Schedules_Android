package com.github.igorilin13.data.teams.api

interface TeamsRepository {
    suspend fun getTeams(): Result<List<Team>>
}