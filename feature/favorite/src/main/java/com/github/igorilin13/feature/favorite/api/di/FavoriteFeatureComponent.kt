package com.github.igorilin13.feature.favorite.api.di

import com.github.igorilin13.feature.favorite.impl.favorite.di.ChangeFavoriteScreenComponent
import com.github.igorilin13.feature.favorite.impl.onboarding.di.OnboardingScreenComponent
import dagger.Subcomponent

@Subcomponent
abstract class FavoriteFeatureComponent {

    internal abstract fun onboardingScreenComponentFactory(): OnboardingScreenComponent.Factory

    internal abstract fun changeFavoriteScreenComponentFactory(): ChangeFavoriteScreenComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteFeatureComponent
    }
}
