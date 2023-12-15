package com.github.igorilin13.feature.team.games.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApi
import com.github.igorilin13.feature.team.games.api.di.TeamGamesFeatureComponent
import com.github.igorilin13.feature.team.games.impl.favorite.FavoriteTeamGamesDestination

internal class TeamGamesFeatureApiImpl(component: TeamGamesFeatureComponent) : TeamGamesFeatureApi {
    private val favoriteTeamGamesDestination =
        FavoriteTeamGamesDestination(component.favoriteGamesScreenComponent())

    override fun registerUi(
        navGraphBuilder: NavGraphBuilder,
        route: String,
        onOpenFavoriteSelection: () -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination = favoriteTeamGamesDestination.fullRoute,
            route = route
        ) {
            favoriteTeamGamesDestination.register(this, onOpenFavoriteSelection)
        }
    }
}