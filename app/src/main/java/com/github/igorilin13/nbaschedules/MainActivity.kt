package com.github.igorilin13.nbaschedules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.igorilin13.data.settings.api.SettingsRepository
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApi
import com.github.igorilin13.feature.favorite.api.FavoriteFeatureApiFactory
import com.github.igorilin13.nbaschedules.ui.theme.NBASchedulesTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private lateinit var favoriteFeatureApi: FavoriteFeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val appComponent = (application as NbaApplication).appComponent
        appComponent.inject(this)

        favoriteFeatureApi = FavoriteFeatureApiFactory.create(appComponent.favoriteSubcomponent())

        setContent {
            NBASchedulesTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = ROUTE_START_SCREEN) {
                    composable(ROUTE_START_SCREEN) {
                        StartScreen(navController)
                    }

                    favoriteFeatureApi.registerUi(this)
                }
            }
        }
    }

    @Composable
    fun StartScreen(navController: NavHostController) {
        LaunchedEffect(Unit) {
            if (settingsRepository.isOnboardingComplete()) {

            } else {
                favoriteFeatureApi.navigateToOnboarding(navController)
            }
        }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    private companion object {
        private const val ROUTE_START_SCREEN = "start"
    }
}
