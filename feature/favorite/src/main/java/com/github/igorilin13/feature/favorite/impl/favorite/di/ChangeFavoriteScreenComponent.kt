package com.github.igorilin13.feature.favorite.impl.favorite.di

import com.github.igorilin13.feature.favorite.impl.favorite.ChangeFavoriteViewModel
import dagger.Subcomponent

@Subcomponent
internal interface ChangeFavoriteScreenComponent {
    fun viewModel(): ChangeFavoriteViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChangeFavoriteScreenComponent
    }
}