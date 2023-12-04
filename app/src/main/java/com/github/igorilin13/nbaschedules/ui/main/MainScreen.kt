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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApi

@Composable
fun MainScreen(teamGamesFeatureApi: TeamGamesFeatureApi) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation()
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = teamGamesFeatureApi.favoriteTeamGamesRoute(),
            modifier = Modifier.padding(paddingValues)
        ) {
            teamGamesFeatureApi.registerUi(this, onOpenFavoriteSelection = {})
        }
    }
}

@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(modifier) {
        MainNavigationItem.values().forEach { item ->
            NavigationBarItem(
                selected = item == MainNavigationItem.FAVORITE_TEAM,
                label = {
                    Text(stringResource(item.label), style = MaterialTheme.typography.bodyMedium)
                },
                icon = {
                    Icon(item.icon, contentDescription = stringResource(item.label))
                },
                onClick = {

                }
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
