package com.github.igorilin13.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.github.igorilin13.conventions.util.appConfig
import com.github.igorilin13.conventions.util.configureJavaVersion
import com.github.igorilin13.conventions.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-application").get().get().pluginId)
                apply(libs.findPlugin("kotlin.android").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = appConfig.findVersion("compileSdk").get().toString().toInt()
                configureJavaVersion(this)

                defaultConfig {
                    targetSdk = appConfig.findVersion("targetSdk").get().toString().toInt()
                    minSdk = appConfig.findVersion("minSdk").get().toString().toInt()
                }
            }
        }
    }
}