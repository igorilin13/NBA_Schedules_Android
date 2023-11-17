package com.github.igorilin13.nbaschedules.di

import android.content.Context
import com.github.igorilin13.data.settings.api.di.SettingsModule
import com.github.igorilin13.nbaschedules.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SettingsModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}