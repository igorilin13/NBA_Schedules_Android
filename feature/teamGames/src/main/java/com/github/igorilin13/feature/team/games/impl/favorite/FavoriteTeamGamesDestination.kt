package com.github.igorilin13.feature.team.games.impl.favorite

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.feature.team.games.impl.favorite.composables.FavoriteTeamGamesScreen
import com.github.igorilin13.feature.team.games.impl.favorite.di.FavoriteTeamGamesScreenComponent

internal class FavoriteTeamGamesDestination(
    private val componentFactory: FavoriteTeamGamesScreenComponent.Factory
) : ScreenDestination {
    override val fullRoute = "favorite_team_games"

    fun register(
        navGraphBuilder: NavGraphBuilder,
        onOpenFavoriteSelection: () -> Unit
    ) = navGraphBuilder.composable(fullRoute) {
        val viewModel = viewModel { componentFactory.create().viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        FavoriteTeamGamesScreen(state, onSelectFavoriteClick = onOpenFavoriteSelection)
    }
}