package com.github.igorilin13.nbaschedules

import android.app.Application
import com.github.igorilin13.nbaschedules.di.DaggerApplicationComponent

class NbaApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}