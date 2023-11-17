package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.ScreenDestination
import com.github.igorilin13.feature.favorite.impl.onboarding.di.OnboardingScreenComponent

class SelectFavoriteOnboardingDestination(
    private val factory: OnboardingScreenComponent.Factory
) : ScreenDestination {

    override val fullRoute = "onboarding"

    fun register(
        navGraphBuilder: NavGraphBuilder
    ) = navGraphBuilder.composable(fullRoute) {
        val component = remember { factory.create() }
        val viewModel = viewModel { component.viewModel() }
        Text("Hi")
    }
}