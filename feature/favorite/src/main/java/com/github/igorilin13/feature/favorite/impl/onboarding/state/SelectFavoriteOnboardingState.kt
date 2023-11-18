package com.github.igorilin13.feature.favorite.impl.onboarding.state

import com.github.igorilin13.data.teams.api.Team

internal sealed interface SelectFavoriteOnboardingState {
    data object Loading : SelectFavoriteOnboardingState
    data object Error : SelectFavoriteOnboardingState
    data class Display(
        val teams: List<Team>,
        val selectedId: Int?
    ) : SelectFavoriteOnboardingState
}