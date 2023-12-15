package com.github.igorilin13.nbaschedules.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.igorilin13.nbaschedules.R

enum class MainNavigationItem(val icon: ImageVector, @StringRes val label: Int, val route: String) {
    FAVORITE_TEAM(Icons.Filled.Favorite, R.string.navigation_favorite, "main_favorite"),
    LEAGUE(Icons.Filled.List, R.string.navigation_league, "main_league"),
    SETTINGS(Icons.Outlined.Settings, R.string.navigation_settings, "main_settings"),
}