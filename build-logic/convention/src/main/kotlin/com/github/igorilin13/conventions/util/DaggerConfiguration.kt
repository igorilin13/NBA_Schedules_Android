package com.github.igorilin13.conventions.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureDagger(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        with(pluginManager) {
            apply(libs.findPlugin("kotlin-ksp").get().get().pluginId)
        }

        dependencies {
            add("implementation", libs.findLibrary("dagger").get())
            add("ksp", libs.findLibrary("dagger.compiler").get())
        }
    }
}