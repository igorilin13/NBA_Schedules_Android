package com.github.igorilin13.league.games.impl.games.di

import com.github.igorilin13.data.games.api.di.GamesModule
import com.github.igorilin13.league.games.impl.games.LeagueGamesViewModel
import dagger.Subcomponent

@Subcomponent(modules = [GamesModule::class])
internal interface LeagueGamesScreenComponent {
    fun viewModel(): LeagueGamesViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): LeagueGamesScreenComponent
    }
}