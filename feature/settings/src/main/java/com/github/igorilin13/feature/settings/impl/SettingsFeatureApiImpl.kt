package com.github.igorilin13.feature.settings.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.github.igorilin13.feature.settings.api.SettingsFeatureApi
import com.github.igorilin13.feature.settings.api.di.SettingsFeatureComponent
import com.github.igorilin13.feature.settings.impl.settings.SettingsDestination

internal class SettingsFeatureApiImpl(
    component: SettingsFeatureComponent
) : SettingsFeatureApi {

    private val settingsDestination = SettingsDestination(component.settingsScreenComponent())

    override fun registerUi(
        navGraphBuilder: NavGraphBuilder,
        route: String,
        openFavoriteSelection: () -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination = settingsDestination.fullRoute,
            route = route
        ) {
            settingsDestination.register(this, openFavoriteSelection)
        }
    }

}