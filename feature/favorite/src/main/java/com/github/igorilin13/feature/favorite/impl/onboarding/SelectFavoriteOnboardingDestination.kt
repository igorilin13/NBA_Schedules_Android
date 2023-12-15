package com.github.igorilin13.feature.favorite.impl.onboarding

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.feature.favorite.R
import com.github.igorilin13.feature.favorite.impl.core.composables.SelectFavoriteTeamScreen
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamUiEvent
import com.github.igorilin13.feature.favorite.impl.onboarding.di.OnboardingScreenComponent

internal class SelectFavoriteOnboardingDestination(
    private val componentFactory: OnboardingScreenComponent.Factory
) : ScreenDestination {

    override val fullRoute = "onboarding"

    fun register(
        navGraphBuilder: NavGraphBuilder,
        onOnboardingComplete: () -> Unit
    ) = navGraphBuilder.composable(fullRoute) {
        val viewModel = viewModel { componentFactory.create().viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    SelectFavoriteTeamUiEvent.SelectionComplete -> onOnboardingComplete()
                }
            }
        }

        SelectFavoriteTeamScreen(
            state,
            onSkipClick = viewModel::skipOnboarding,
            onTeamClick = viewModel::selectTeam,
            onConfirmClick = viewModel::confirmSelection,
            saveTeamButtonText = stringResource(R.string.action_continue)
        )
    }
}