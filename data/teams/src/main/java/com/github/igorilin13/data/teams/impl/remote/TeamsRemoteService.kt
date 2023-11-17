package com.github.igorilin13.data.teams.impl.remote

import retrofit2.http.GET

internal interface TeamsRemoteService {
    @GET("api/v1/teams?per_page=100")
    suspend fun getTeams(): TeamListResponse
}