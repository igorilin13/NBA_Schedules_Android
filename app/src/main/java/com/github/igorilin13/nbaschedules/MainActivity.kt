package com.github.igorilin13.nbaschedules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.igorilin13.common.ui.theme.NBASchedulesTheme
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApi
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApiFactory
import com.github.igorilin13.feature.settings.api.SettingsFeatureApi
import com.github.igorilin13.feature.settings.api.SettingsFeatureApiFactory
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApi
import com.github.igorilin13.feature.team.games.api.TeamGamesFeatureApiFactory
import com.github.igorilin13.league.games.api.LeagueGamesFeatureApi
import com.github.igorilin13.league.games.api.LeagueGamesFeatureApiFactory
import com.github.igorilin13.nbaschedules.di.ApplicationComponent
import com.github.igorilin13.nbaschedules.ui.main.MainScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private lateinit var favoriteFeatureApi: FavoriteFeatureApi
    private lateinit var teamGamesFeatureApi: TeamGamesFeatureApi
    private lateinit var leagueGamesFeatureApi: LeagueGamesFeatureApi
    private lateinit var settingsFeatureApi: SettingsFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val appComponent = (application as NbaApplication).appComponent
        appComponent.inject(this)
        createFeatureApis(appComponent)

        setContent {
            NBASchedulesTheme {
                Content()
            }
        }
    }

    @Composable
    internal fun Content() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = ROUTE_START_SCREEN) {
            composable(ROUTE_START_SCREEN) {
                StartScreen(navController)
            }

            composable(ROUTE_MAIN_SCREEN) {
                MainScreen(
                    teamGamesFeatureApi,
                    leagueGamesFeatureApi,
                    settingsFeatureApi,
                    openFavoriteSelection = {
                        navController.navigate(favoriteFeatureApi.changeFavoriteNavigationRoute())
                    }
                )
            }

            favoriteFeatureApi.registerUi(
                this,
                onOnboardingComplete = { navController.navigate(ROUTE_MAIN_SCREEN) },
                onFavoriteChangeComplete = { navController.popBackStack() }
            )
        }
    }

    private fun createFeatureApis(appComponent: ApplicationComponent) {
        favoriteFeatureApi =
            FavoriteFeatureApiFactory.create(appComponent.favoriteFeatureSubcomponent())
        teamGamesFeatureApi =
            TeamGamesFeatureApiFactory.create(appComponent.teamGamesFeatureSubcomponent())
        leagueGamesFeatureApi =
            LeagueGamesFeatureApiFactory.create(appComponent.leagueGamesFeatureSubcomponent())
        settingsFeatureApi =
            SettingsFeatureApiFactory.create(appComponent.settingsFeatureSubcomponent())
    }

    @Composable
    fun StartScreen(navController: NavHostController) {
        LaunchedEffect(Unit) {
            if (settingsRepository.isOnboardingComplete()) {
                navController.navigate(ROUTE_MAIN_SCREEN) {
                    popUpTo(ROUTE_START_SCREEN) { inclusive = true }
                }
            } else {
                navController.navigate(favoriteFeatureApi.onboardingNavigationRoute()) {
                    popUpTo(ROUTE_START_SCREEN) { inclusive = true }
                }
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    private companion object {
        private const val ROUTE_START_SCREEN = "start"
        private const val ROUTE_MAIN_SCREEN = "main"
    }
}
