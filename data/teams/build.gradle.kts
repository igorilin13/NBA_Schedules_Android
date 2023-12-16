plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.dagger)
    alias(libs.plugins.room)
}

android {
    namespace = "com.github.igorilin13.data.teams"
    defaultConfig {
        room {
            schemaDirectory("$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(projects.common.network)
    implementation(libs.bundles.network)
    ksp(libs.moshi.codegen)
    implementation(libs.room)
    ksp(libs.room.compiler)
}