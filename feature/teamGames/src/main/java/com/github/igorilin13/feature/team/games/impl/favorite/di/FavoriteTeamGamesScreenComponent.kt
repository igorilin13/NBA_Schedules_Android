package com.github.igorilin13.feature.team.games.impl.favorite.di

import com.github.igorilin13.data.games.api.di.GamesModule
import com.github.igorilin13.feature.team.games.impl.favorite.FavoriteTeamGamesViewModel
import dagger.Subcomponent

@Subcomponent(modules = [GamesModule::class])
internal interface FavoriteTeamGamesScreenComponent {
    fun viewModel(): FavoriteTeamGamesViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteTeamGamesScreenComponent
    }
}