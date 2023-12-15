package com.github.igorilin13.league.games.api

import androidx.navigation.NavGraphBuilder

interface LeagueGamesFeatureApi {
    fun registerUi(navGraphBuilder: NavGraphBuilder, route: String)
}