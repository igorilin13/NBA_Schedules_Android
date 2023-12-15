package com.github.igorilin13.feature.favorite.impl.onboarding.di

import com.github.igorilin13.feature.favorite.impl.onboarding.SelectFavoriteOnboardingViewModel
import dagger.Subcomponent

@Subcomponent
internal interface OnboardingScreenComponent {
    fun viewModel(): SelectFavoriteOnboardingViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): OnboardingScreenComponent
    }
}