package com.github.igorilin13.feature.team.games.api

import androidx.navigation.NavGraphBuilder

interface TeamGamesFeatureApi {
    fun registerUi(
        navGraphBuilder: NavGraphBuilder,
        route: String,
        onOpenFavoriteSelection: () -> Unit
    )
}