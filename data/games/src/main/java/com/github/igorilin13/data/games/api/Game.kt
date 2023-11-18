package com.github.igorilin13.data.games.api

import com.github.igorilin13.data.teams.api.Team

data class Game(
    val id: Long,
    val date: String,
    val homeTeam: Team,
    val homeTeamScore: Int,
    val postseason: Boolean,
    val time: String?,
    val visitorTeamScore: Int,
    val visitorTeam: Team,
)