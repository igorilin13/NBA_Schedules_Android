package com.github.igorilin13.feature.favorite.api

import androidx.navigation.NavGraphBuilder

interface FavoriteFeatureApi {
    fun registerUi(navGraphBuilder: NavGraphBuilder, onOnboardingComplete: () -> Unit)

    fun onboardingNavigationRoute(): String
}
