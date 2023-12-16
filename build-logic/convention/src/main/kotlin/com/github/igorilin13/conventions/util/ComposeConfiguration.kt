package com.github.igorilin13.conventions.util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose.compiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findBundle("compose").get())

            add("debugImplementation", libs.findBundle("compose.debug").get())

            add("androidTestImplementation", platform(bom))
            add("androidTestImplementation", libs.findLibrary("compose.ui.test.junit4").get())
        }
    }
}