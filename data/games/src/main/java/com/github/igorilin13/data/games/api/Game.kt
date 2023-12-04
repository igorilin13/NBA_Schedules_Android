package com.github.igorilin13.data.games.api

import com.github.igorilin13.data.teams.api.Team
import java.time.ZonedDateTime

data class Game(
    val id: Long,
    val date: ZonedDateTime,
    val homeTeam: Team,
    val homeTeamScore: Int,
    val postseason: Boolean,
    val time: String?,
    val visitorTeamScore: Int,
    val visitorTeam: Team,
    val gameStatus: GameStatus
)

enum class GameStatus {
    SCHEDULED,
    LIVE,
    FINISHED
}