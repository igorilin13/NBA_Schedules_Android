package com.github.igorilin13.feature.settings.api.di

import com.github.igorilin13.feature.settings.impl.settings.di.SettingsScreenComponent
import dagger.Subcomponent

@Subcomponent
abstract class SettingsFeatureComponent {

    internal abstract fun settingsScreenComponent(): SettingsScreenComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsFeatureComponent
    }
}