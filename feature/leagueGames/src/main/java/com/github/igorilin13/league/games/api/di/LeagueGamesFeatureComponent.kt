package com.github.igorilin13.league.games.api.di

import com.github.igorilin13.league.games.impl.games.di.LeagueGamesScreenComponent
import dagger.Subcomponent

@Subcomponent
abstract class LeagueGamesFeatureComponent {

    internal abstract fun leagueGamesScreenComponent(): LeagueGamesScreenComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): LeagueGamesFeatureComponent
    }
}