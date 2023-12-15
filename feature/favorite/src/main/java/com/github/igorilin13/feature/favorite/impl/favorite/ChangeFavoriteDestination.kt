package com.github.igorilin13.feature.favorite.impl.favorite

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.feature.favorite.R
import com.github.igorilin13.feature.favorite.impl.core.composables.SelectFavoriteTeamScreen
import com.github.igorilin13.feature.favorite.impl.core.state.SelectFavoriteTeamUiEvent
import com.github.igorilin13.feature.favorite.impl.favorite.di.ChangeFavoriteScreenComponent

internal class ChangeFavoriteDestination(
    private val componentFactory: ChangeFavoriteScreenComponent.Factory
) : ScreenDestination {

    override val fullRoute = "change_favorite"

    fun register(
        navGraphBuilder: NavGraphBuilder,
        onFavoriteChangeComplete: () -> Unit,
    ) = navGraphBuilder.composable(fullRoute) {
        val component = remember { componentFactory.create() }
        val viewModel = viewModel { component.viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    SelectFavoriteTeamUiEvent.SelectionComplete -> onFavoriteChangeComplete()
                }
            }
        }

        SelectFavoriteTeamScreen(
            state,
            onSkipClick = null,
            onTeamClick = viewModel::selectTeam,
            onConfirmClick = viewModel::confirmSelection,
            saveTeamButtonText = stringResource(R.string.action_confirm)
        )
    }
}