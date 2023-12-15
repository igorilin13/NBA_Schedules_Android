package com.github.igorilin13.feature.settings.api

import androidx.navigation.NavGraphBuilder

interface SettingsFeatureApi {
    fun registerUi(navGraphBuilder: NavGraphBuilder, route: String)
}