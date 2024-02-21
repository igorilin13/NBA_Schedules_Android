package com.github.igorilin13.nbaschedules

import android.app.Application
import com.github.igorilin13.nbaschedules.di.DaggerApplicationComponent

class NbaApplication : Application() {
    val appComponent = DaggerApplicationComponent.factory().create(
        context = this,
        apiUrl = BuildConfig.API_URL,
        apiKey = BuildConfig.API_KEY
    )
}