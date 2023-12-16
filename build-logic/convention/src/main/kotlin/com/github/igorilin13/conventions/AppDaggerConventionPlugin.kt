package com.github.igorilin13.conventions

import com.android.build.api.dsl.ApplicationExtension
import com.github.igorilin13.conventions.util.configureDagger
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AppDaggerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureDagger(extensions.getByType<ApplicationExtension>())
        }
    }
}
