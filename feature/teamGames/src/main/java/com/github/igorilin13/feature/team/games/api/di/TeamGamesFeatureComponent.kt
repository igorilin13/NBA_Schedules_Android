package com.github.igorilin13.feature.team.games.api.di

import com.github.igorilin13.feature.team.games.impl.favorite.di.FavoriteTeamGamesScreenComponent
import dagger.Subcomponent

@Subcomponent
abstract class TeamGamesFeatureComponent {

    internal abstract fun favoriteGamesScreenComponent(): FavoriteTeamGamesScreenComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): TeamGamesFeatureComponent
    }
}