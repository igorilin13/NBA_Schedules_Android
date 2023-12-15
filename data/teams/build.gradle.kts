plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.room)
}

android {
    namespace = "com.github.igorilin13.data.teams"
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = appConfig.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        room {
            schemaDirectory("$projectDir/schemas")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common:network:"))
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.bundles.network)
    ksp(libs.moshi.codegen)
    implementation(libs.kotlin.coroutines)
    implementation(libs.room)
    ksp(libs.room.compiler)
}