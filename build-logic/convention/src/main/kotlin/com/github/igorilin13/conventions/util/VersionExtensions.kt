package com.github.igorilin13.conventions.util

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val Project.appConfig
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("appConfig")