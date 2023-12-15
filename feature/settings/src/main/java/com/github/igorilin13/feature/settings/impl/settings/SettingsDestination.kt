package com.github.igorilin13.feature.settings.impl.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.feature.settings.impl.settings.composables.SettingsScreen
import com.github.igorilin13.feature.settings.impl.settings.di.SettingsScreenComponent

internal class SettingsDestination(
    private val componentFactory: SettingsScreenComponent.Factory
) : ScreenDestination {
    override val fullRoute = "settings"

    fun register(
        navGraphBuilder: NavGraphBuilder,
        openFavoriteSelection: () -> Unit
    ) = navGraphBuilder.composable(fullRoute) {
        val component = remember { componentFactory.create() }
        val viewModel = viewModel { component.viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        SettingsScreen(
            state,
            onHideScoresChange = viewModel::setHideScores,
            onSelectFavoriteTeamClick = openFavoriteSelection
        )
    }
}