package com.github.igorilin13.feature.favorite.impl

import androidx.navigation.NavGraphBuilder
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApi
import com.github.igorilin13.feature.favorite.api.di.FavoriteFeatureComponent
import com.github.igorilin13.feature.favorite.impl.favorite.ChangeFavoriteDestination
import com.github.igorilin13.feature.favorite.impl.onboarding.SelectFavoriteOnboardingDestination

class FavoriteFeatureApiImpl(component: FavoriteFeatureComponent) : FavoriteFeatureApi {
    private val onboardingDestination =
        SelectFavoriteOnboardingDestination(component.onboardingScreenComponentFactory())
    private val changeFavoriteDestination =
        ChangeFavoriteDestination(component.changeFavoriteScreenComponentFactory())

    override fun registerUi(
        navGraphBuilder: NavGraphBuilder,
        onOnboardingComplete: () -> Unit,
        onFavoriteChangeComplete: () -> Unit
    ) {
        onboardingDestination.register(navGraphBuilder, onOnboardingComplete)
        changeFavoriteDestination.register(navGraphBuilder, onFavoriteChangeComplete)
    }

    override fun onboardingNavigationRoute(): String {
        return onboardingDestination.fullRoute
    }

    override fun changeFavoriteNavigationRoute(): String {
        return changeFavoriteDestination.fullRoute
    }
}