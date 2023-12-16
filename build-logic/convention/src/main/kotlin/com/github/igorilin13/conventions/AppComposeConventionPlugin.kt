package com.github.igorilin13.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.github.igorilin13.conventions.util.configureCompose
import com.github.igorilin13.conventions.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AppComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(libs.findPlugin("android-application").get().get().pluginId)

            configureCompose(extensions.getByType<ApplicationExtension>())
        }
    }

}