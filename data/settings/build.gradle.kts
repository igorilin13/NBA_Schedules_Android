plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.dagger)
}

android {
    namespace = "com.github.igorilin13.data.settings"
}

dependencies {
    api(libs.datastore)
}