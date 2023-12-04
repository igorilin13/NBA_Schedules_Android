package com.github.igorilin13.nbaschedules.di

import android.content.Context
import com.github.igorilin13.common.network.NetworkModule
import com.github.igorilin13.data.settings.api.di.SettingsModule
import com.github.igorilin13.feature.favorite.api.di.FavoriteFeatureComponent
import com.github.igorilin13.feature.team.games.api.di.TeamGamesFeatureComponent
import com.github.igorilin13.nbaschedules.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TextProviderModule::class, NetworkModule::class, SettingsModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun favoriteFeatureSubcomponent(): FavoriteFeatureComponent.Factory

    fun teamGamesFeatureSubcomponent(): TeamGamesFeatureComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance baseServiceUrl: String
        ): ApplicationComponent
    }
}