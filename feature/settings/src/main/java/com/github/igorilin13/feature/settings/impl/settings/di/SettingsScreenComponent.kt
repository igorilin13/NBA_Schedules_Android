package com.github.igorilin13.feature.settings.impl.settings.di

import com.github.igorilin13.feature.settings.impl.settings.SettingsViewModel
import dagger.Subcomponent

@Subcomponent
internal interface SettingsScreenComponent {
    fun viewModel(): SettingsViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): SettingsScreenComponent
    }
}