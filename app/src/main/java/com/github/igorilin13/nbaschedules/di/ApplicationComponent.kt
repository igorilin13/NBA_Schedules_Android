package com.github.igorilin13.nbaschedules.di

import android.content.Context
import com.github.igorilin13.common.network.ApiKeyQualifier
import com.github.igorilin13.common.network.ApiUrlQualifier
import com.github.igorilin13.common.network.NetworkModule
import com.github.igorilin13.data.settings.api.di.SettingsModule
import com.github.igorilin13.data.teams.api.di.TeamsModule
import com.github.igorilin13.feature.favorite.api.di.FavoriteFeatureComponent
import com.github.igorilin13.feature.settings.api.di.SettingsFeatureComponent
import com.github.igorilin13.feature.team.games.api.di.TeamGamesFeatureComponent
import com.github.igorilin13.league.games.api.di.LeagueGamesFeatureComponent
import com.github.igorilin13.nbaschedules.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TeamsModule::class,
        TextProviderModule::class,
        NetworkModule::class,
        SettingsModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun favoriteFeatureSubcomponent(): FavoriteFeatureComponent.Factory
    fun teamGamesFeatureSubcomponent(): TeamGamesFeatureComponent.Factory
    fun leagueGamesFeatureSubcomponent(): LeagueGamesFeatureComponent.Factory
    fun settingsFeatureSubcomponent(): SettingsFeatureComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance @ApiUrlQualifier apiUrl: String,
            @BindsInstance @ApiKeyQualifier apiKey: String,
        ): ApplicationComponent
    }
}