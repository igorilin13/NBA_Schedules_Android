plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.dagger)
}

android {
    namespace = "com.github.igorilin13.domain"
}

dependencies {
    implementation(projects.data.games)
    implementation(projects.data.settings)
}