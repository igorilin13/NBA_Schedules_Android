package com.github.igorilin13.conventions

import com.android.build.gradle.LibraryExtension
import com.github.igorilin13.conventions.util.appConfig
import com.github.igorilin13.conventions.util.configureJavaVersion
import com.github.igorilin13.conventions.util.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class LibraryModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
                apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            }

            extensions.getByType<LibraryExtension>().apply {
                compileSdk = appConfig.findVersion("compileSdk").get().toString().toInt()

                defaultConfig {
                    minSdk = appConfig.findVersion("minSdk").get().toString().toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
                configureJavaVersion(this)

                dependencies {
                    add("implementation", libs.findLibrary("kotlin.coroutines").get())
                }
            }
        }
    }

}
