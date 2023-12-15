package com.github.igorilin13.domain.game

import com.github.igorilin13.data.games.api.Game

data class GameItem(
    val model: Game,
    val formattedDate: String,
    val showScores: Boolean
)