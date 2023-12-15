package com.github.igorilin13.league.games.api

import com.github.igorilin13.league.games.api.di.LeagueGamesFeatureComponent
import com.github.igorilin13.league.games.impl.LeagueGamesFeatureApiImpl

object LeagueGamesFeatureApiFactory {
    fun create(componentFactory: LeagueGamesFeatureComponent.Factory): LeagueGamesFeatureApi {
        return LeagueGamesFeatureApiImpl(componentFactory.create())
    }
}