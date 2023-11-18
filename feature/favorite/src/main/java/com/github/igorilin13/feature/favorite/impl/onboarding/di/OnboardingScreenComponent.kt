package com.github.igorilin13.feature.favorite.impl.onboarding.di

import com.github.igorilin13.data.teams.api.di.TeamsModule
import com.github.igorilin13.feature.favorite.impl.onboarding.SelectFavoriteOnboardingViewModel
import dagger.Subcomponent

@Subcomponent(modules = [TeamsModule::class])
internal interface OnboardingScreenComponent {
    fun viewModel(): SelectFavoriteOnboardingViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): OnboardingScreenComponent
    }
}