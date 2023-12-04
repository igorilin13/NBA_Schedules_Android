package com.github.igorilin13.feature.team.games.impl

import androidx.navigation.NavGraphBuilder
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApi
import com.github.igorilin13.feature.team.games.api.di.TeamGamesFeatureComponent
import com.github.igorilin13.feature.team.games.impl.favorite.FavoriteTeamGamesDestination

internal class TeamGamesFeatureApiImpl(component: TeamGamesFeatureComponent) : TeamGamesFeatureApi {
    private val favoriteTeamGamesDestination =
        FavoriteTeamGamesDestination(component.favoriteGamesScreenComponent())

    override fun registerUi(navGraphBuilder: NavGraphBuilder, onOpenFavoriteSelection: () -> Unit) {
        favoriteTeamGamesDestination.register(navGraphBuilder, onOpenFavoriteSelection)
    }

    override fun favoriteTeamGamesRoute(): String {
        return favoriteTeamGamesDestination.fullRoute
    }
}