package com.github.igorilin13.data.teams.api.di

import com.github.igorilin13.data.teams.impl.di.TeamsModuleInternal
import dagger.Module

@Module(includes = [TeamsModuleInternal::class])
interface TeamsModule