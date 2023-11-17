package com.github.igorilin13.feature.favorite.impl.onboarding.di

import com.github.igorilin13.feature.favorite.impl.onboarding.OnboardingViewModel
import dagger.Subcomponent

@Subcomponent
interface OnboardingScreenComponent {
    fun viewModel(): OnboardingViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): OnboardingScreenComponent
    }
}