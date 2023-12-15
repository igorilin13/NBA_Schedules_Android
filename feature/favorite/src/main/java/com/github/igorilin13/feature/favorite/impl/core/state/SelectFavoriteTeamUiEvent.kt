package com.github.igorilin13.feature.favorite.impl.core.state

internal sealed interface SelectFavoriteTeamUiEvent {
    data object SelectionComplete : SelectFavoriteTeamUiEvent
}