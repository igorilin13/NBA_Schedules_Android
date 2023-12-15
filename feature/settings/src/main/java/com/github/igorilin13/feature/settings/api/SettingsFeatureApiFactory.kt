package com.github.igorilin13.feature.settings.api

import com.github.igorilin13.feature.settings.api.di.SettingsFeatureComponent
import com.github.igorilin13.feature.settings.impl.SettingsFeatureApiImpl

object SettingsFeatureApiFactory {
    fun create(componentFactory: SettingsFeatureComponent.Factory): SettingsFeatureApi {
        return SettingsFeatureApiImpl(componentFactory.create())
    }
}