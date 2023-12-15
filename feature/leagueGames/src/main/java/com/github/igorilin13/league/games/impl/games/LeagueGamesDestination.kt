package com.github.igorilin13.league.games.impl.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.github.igorilin13.common.ui.screen.ScreenDestination
import com.github.igorilin13.league.games.impl.games.composables.LeagueGamesScreen
import com.github.igorilin13.league.games.impl.games.di.LeagueGamesScreenComponent

internal class LeagueGamesDestination(
    private val componentFactory: LeagueGamesScreenComponent.Factory
) : ScreenDestination {
    override val fullRoute = "league_games"

    fun register(
        navGraphBuilder: NavGraphBuilder
    ) = navGraphBuilder.composable(fullRoute) {
        val component = remember { componentFactory.create() }
        val viewModel = viewModel { component.viewModel() }
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LeagueGamesScreen(state)
    }
}