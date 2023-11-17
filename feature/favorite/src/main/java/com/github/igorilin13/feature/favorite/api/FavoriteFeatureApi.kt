package com.github.igorilin13.feature.favorite.api

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FavoriteFeatureApi {
    fun registerUi(navGraphBuilder: NavGraphBuilder)

    fun navigateToOnboarding(navController: NavController)
}
