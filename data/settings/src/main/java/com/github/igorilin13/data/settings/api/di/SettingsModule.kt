package com.github.igorilin13.data.settings.api.di

import com.github.igorilin13.data.settings.impl.di.SettingsModuleInternal
import dagger.Module

@Module(includes = [SettingsModuleInternal::class])
interface SettingsModule