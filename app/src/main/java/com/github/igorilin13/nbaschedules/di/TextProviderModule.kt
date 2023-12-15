package com.github.igorilin13.nbaschedules.di

import com.github.igorilin13.domain.date.DateTextProvider
import com.github.igorilin13.nbaschedules.formatter.ContextDateTextProvider
import dagger.Binds
import dagger.Module

@Module
interface TextProviderModule {
    @Binds
    fun textProvider(provider: ContextDateTextProvider): DateTextProvider
}