package com.github.igorilin13.feature.favorite.api

import com.github.igorilin13.feature.favorite.api.di.FavoriteFeatureComponent
import com.github.igorilin13.feature.favorite.impl.FavoriteFeatureApiImpl

object FavoriteFeatureApiFactory {
    fun create(componentFactory: FavoriteFeatureComponent.Factory): FavoriteFeatureApi {
        return FavoriteFeatureApiImpl(componentFactory.create())
    }
}