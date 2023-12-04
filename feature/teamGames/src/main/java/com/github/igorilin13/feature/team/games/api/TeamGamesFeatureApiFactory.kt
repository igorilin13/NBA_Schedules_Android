package com.github.igorilin13.feature.team.games.api

import com.github.igorilin13.feature.team.games.api.di.TeamGamesFeatureComponent
import com.github.igorilin13.feature.team.games.impl.TeamGamesFeatureApiImpl

object TeamGamesFeatureApiFactory {
    fun create(componentFactory: TeamGamesFeatureComponent.Factory): TeamGamesFeatureApi {
        return TeamGamesFeatureApiImpl(componentFactory.create())
    }
}