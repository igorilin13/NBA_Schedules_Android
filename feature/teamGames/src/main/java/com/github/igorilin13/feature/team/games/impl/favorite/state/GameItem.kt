package com.github.igorilin13.feature.team.games.impl.favorite.state

import com.github.igorilin13.data.games.api.Game

internal data class GameItem(
    val model: Game,
    val formattedDate: String,
    val showScores: Boolean
)