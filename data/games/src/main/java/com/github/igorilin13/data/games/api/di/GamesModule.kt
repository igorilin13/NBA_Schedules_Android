package com.github.igorilin13.data.games.api.di

import com.github.igorilin13.data.games.impl.di.GamesModuleInternal
import dagger.Module

@Module(includes = [GamesModuleInternal::class])
interface GamesModule