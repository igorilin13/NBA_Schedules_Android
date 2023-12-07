package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.feature.favorite.impl.onboarding.composables.SelectFavoriteOnboardingScreen
import com.github.igorilin13.feature.favorite.impl.onboarding.di.OnboardingScreenComponent
import com.github.igorilin13.feature.favorite.impl.onboarding.state.SelectFavoriteOnboardingUiEvent

internal class SelectFavoriteOnboardingDestination(
    private val componentFactory: OnboardingScreenComponent.Factory
) : ScreenDestination {

    override val fullRoute = "onboarding"

    fun register(
        navGraphBuilder: NavGraphBuilder,
        onOnboardingComplete: () -> Unit
    ) = navGraphBuilder.composable(fullRoute) {
        val component = remember { componentFactory.create() }
        val viewModel = viewModel { component.viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    SelectFavoriteOnboardingUiEvent.OnboardingComplete -> onOnboardingComplete()
                }
            }
        }

        SelectFavoriteOnboardingScreen(
            state,
            onSkipClick = viewModel::skipOnboarding,
            onTeamClick = viewModel::selectTeam,
            onConfirmClick = viewModel::confirmSelection
        )
    }
}