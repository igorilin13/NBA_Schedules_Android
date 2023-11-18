package com.github.igorilin13.feature.favorite.impl

import androidx.navigation.NavGraphBuilder
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApi
import com.github.igorilin13.feature.favorite.api.di.FavoriteFeatureComponent
import com.github.igorilin13.feature.favorite.impl.onboarding.SelectFavoriteOnboardingDestination

class FavoriteFeatureApiImpl(component: FavoriteFeatureComponent) : FavoriteFeatureApi {
    private val onboardingDestination =
        SelectFavoriteOnboardingDestination(component.onboardingScreenComponentFactory())

    override fun registerUi(navGraphBuilder: NavGraphBuilder, onOnboardingComplete: () -> Unit) {
        onboardingDestination.register(navGraphBuilder, onOnboardingComplete)
    }

    override fun onboardingNavigationRoute(): String {
        return onboardingDestination.fullRoute
    }
}