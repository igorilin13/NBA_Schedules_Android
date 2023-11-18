package com.github.igorilin13.data.games.impl.remote

import com.github.igorilin13.common.network.MetaDataResponse
import com.github.igorilin13.common.network.TeamResponse
import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.teams.impl.remote.toModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class GamesResponse(
    val data: List<GameResponse>,
    val meta: MetaDataResponse
)

@JsonClass(generateAdapter = true)
internal data class GameResponse(
    val id: Long,
    val date: String,
    @Json(name = "home_team")
    val homeTeam: TeamResponse,
    @Json(name = "home_team_score")
    val homeTeamScore: Int,
    val period: Int,
    val postseason: Boolean,
    val status: String,
    val time: String?,
    @Json(name = "visitor_team_score")
    val visitorTeamScore: Int,
    @Json(name = "visitor_team")
    val visitorTeam: TeamResponse,
)

internal fun GameResponse.toModel(): Game {
    return Game(
        id = id,
        date = date,
        homeTeam = homeTeam.toModel(),
        homeTeamScore = homeTeamScore,
        postseason = postseason,
        time = time,
        visitorTeamScore = visitorTeamScore,
        visitorTeam = visitorTeam.toModel()
    )
}