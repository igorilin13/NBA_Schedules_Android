plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.github.igorilin13.nbaschedules"
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.github.igorilin13.nbaschedules"
        minSdk = appConfig.versions.minSdk.get().toInt()
        targetSdk = appConfig.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "SERVICE_URL", "\"https://www.balldontlie.io/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.favorite)
    implementation(projects.feature.teamGames)
    implementation(projects.feature.leagueGames)
    implementation(projects.feature.settings)
    implementation(projects.data.settings)
    implementation(projects.data.teams)
    implementation(projects.data.games)
    implementation(projects.common.network)
    implementation(projects.common.ui)
    implementation(projects.domain)

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    implementation(libs.datastore)
    implementation(libs.bundles.network)

    debugImplementation(libs.bundles.compose.debug)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
}