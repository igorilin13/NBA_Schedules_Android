plugins {
    alias(libs.plugins.convention.library)
    alias(libs.plugins.convention.library.dagger)
}

android {
    namespace = "com.github.igorilin13.common.network"
}

dependencies {
    implementation(libs.bundles.network)
    ksp(libs.moshi.codegen)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)
}