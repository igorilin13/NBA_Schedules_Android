import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.github.igorilin13.build.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("libraryModule") {
            id = "nbaschedules.convention.library"
            implementationClass = "com.github.igorilin13.conventions.LibraryModuleConventionPlugin"
        }
        register("configureLibraryCompose") {
            id = "nbaschedules.convention.library.compose"
            implementationClass = "com.github.igorilin13.conventions.LibraryComposeConventionPlugin"
        }
        register("configureLibraryDagger") {
            id = "nbaschedules.convention.library.dagger"
            implementationClass = "com.github.igorilin13.conventions.LibraryDaggerConventionPlugin"
        }
        register("appModule") {
            id = "nbaschedules.convention.app"
            implementationClass = "com.github.igorilin13.conventions.AppConventionPlugin"
        }
        register("configureAppCompose") {
            id = "nbaschedules.convention.app.compose"
            implementationClass = "com.github.igorilin13.conventions.AppComposeConventionPlugin"
        }
        register("configureAppDagger") {
            id = "nbaschedules.convention.app.dagger"
            implementationClass = "com.github.igorilin13.conventions.AppDaggerConventionPlugin"
        }
    }
}
