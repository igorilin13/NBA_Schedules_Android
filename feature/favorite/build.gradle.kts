plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.github.igorilin13.feature.favorite"
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = appConfig.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
    implementation(projects.data.settings)
    implementation(projects.data.teams)
    implementation(projects.common.ui)
    implementation(projects.common.util)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.bundles.network)

    debugImplementation(libs.bundles.compose.debug)
}