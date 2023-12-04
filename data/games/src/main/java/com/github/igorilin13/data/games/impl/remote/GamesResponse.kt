package com.github.igorilin13.data.games.impl.remote

import com.github.igorilin13.common.network.MetaDataResponse
import com.github.igorilin13.common.network.TeamResponse
import com.github.igorilin13.data.games.api.Game
import com.github.igorilin13.data.games.api.GameStatus
import com.github.igorilin13.data.teams.impl.remote.toModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

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
    val gameStatus = when {
        time == null -> GameStatus.SCHEDULED
        status.contains("Final", ignoreCase = true) -> GameStatus.FINISHED
        else -> GameStatus.LIVE
    }

    val instant = if (gameStatus == GameStatus.SCHEDULED) {
        try {
            // At the time of writing this, the date field is always incorrect (usually off by a day)
            // For upcoming games, the status field contains the correct date
            Instant.parse(status)
        } catch (e: Exception) {
            Instant.parse(date)
        }
    } else {
        Instant.parse(date)
    }
    val parsedDate = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())

    return Game(
        id = id,
        date = parsedDate,
        homeTeam = homeTeam.toModel(),
        homeTeamScore = homeTeamScore,
        postseason = postseason,
        time = time,
        visitorTeamScore = visitorTeamScore,
        visitorTeam = visitorTeam.toModel(),
        gameStatus = gameStatus
    )
}