package com.github.igorilin13.league.games.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.igorilin13.league.games.api.LeagueGamesFeatureApi
import com.github.igorilin13.league.games.api.di.LeagueGamesFeatureComponent
import com.github.igorilin13.league.games.impl.games.LeagueGamesDestination

internal class LeagueGamesFeatureApiImpl(
    component: LeagueGamesFeatureComponent
) : LeagueGamesFeatureApi {

    private val leagueGamesDestination =
        LeagueGamesDestination(component.leagueGamesScreenComponent())

    override fun registerUi(navGraphBuilder: NavGraphBuilder, route: String) {
        navGraphBuilder.navigation(
            startDestination = leagueGamesDestination.fullRoute,
            route = route
        ) {
            leagueGamesDestination.register(this)
        }
    }

}