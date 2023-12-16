plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.compose)
    alias(libs.plugins.convention.library.dagger)
}

android {
    namespace = "com.github.igorilin13.feature.league.games"
}

dependencies {
    implementation(projects.data.settings)
    implementation(projects.data.games)
    implementation(projects.data.teams)
    implementation(projects.common.ui)
    implementation(projects.common.util)
    implementation(projects.domain)
}