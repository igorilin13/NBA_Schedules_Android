package com.github.igorilin13.feature.favorite.impl.onboarding.state

internal sealed interface SelectFavoriteOnboardingUiEvent {
    data object OnboardingComplete : SelectFavoriteOnboardingUiEvent
}