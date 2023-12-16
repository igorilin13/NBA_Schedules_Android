package com.github.igorilin13.conventions

import com.android.build.api.dsl.LibraryExtension
import com.github.igorilin13.conventions.util.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class LibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose(extensions.getByType<LibraryExtension>())
        }
    }

}
