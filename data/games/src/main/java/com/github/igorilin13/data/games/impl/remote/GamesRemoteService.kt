package com.github.igorilin13.data.games.impl.remote

import retrofit2.http.GET
import retrofit2.http.Query

internal interface GamesRemoteService {
    @GET("games?per_page=100")
    suspend fun getTeamGames(
        @Query("team_ids[]") teamIds: List<Int>,
        @Query("seasons[]") seasons: List<Int>,
        @Query("cursor") cursor: Int?
    ): GamesResponse

    @GET("games?per_page=100")
    suspend fun getLeagueGames(@Query("dates[]") dates: List<String>): GamesResponse
}