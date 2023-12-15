package com.github.igorilin13.nbaschedules.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApi
import com.github.igorilin13.league.games.api.LeagueGamesFeatureApi

@Composable
fun MainScreen(
    teamGamesFeatureApi: TeamGamesFeatureApi,
    leagueGamesFeatureApi: LeagueGamesFeatureApi
) {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                currentDestination = currentEntry?.destination,
                onItemClick = { item ->
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = MainNavigationItem.FAVORITE_TEAM.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            teamGamesFeatureApi.registerUi(this, MainNavigationItem.FAVORITE_TEAM.route) {}
            leagueGamesFeatureApi.registerUi(this, MainNavigationItem.LEAGUE.route)
        }
    }
}

@Composable
private fun BottomNavigation(
    currentDestination: NavDestination?,
    onItemClick: (MainNavigationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier) {
        MainNavigationItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = {
                    Text(stringResource(item.label), style = MaterialTheme.typography.bodyMedium)
                },
                icon = {
                    Icon(item.icon, contentDescription = stringResource(item.label))
                },
                onClick = { onItemClick(item) }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    NBASchedulesTheme {

    }
}
