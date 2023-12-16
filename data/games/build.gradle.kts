plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.dagger)
}

android {
    namespace = "com.github.igorilin13.data.games"
}

dependencies {
    implementation(projects.common.network)
    implementation(projects.data.teams)
    api(libs.bundles.network)
    ksp(libs.moshi.codegen)
}