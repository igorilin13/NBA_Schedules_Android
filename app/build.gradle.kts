import java.util.Properties

plugins {
    alias(libs.plugins.convention.app)
    alias(libs.plugins.convention.app.compose)
    alias(libs.plugins.convention.app.dagger)
}

android {
    namespace = "com.github.igorilin13.nbaschedules"
    compileSdk = appConfig.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.github.igorilin13.nbaschedules"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_URL", "\"https://api.balldontlie.io/v1/\"")
        buildConfigField("String", "API_KEY", "\"${loadApiKey()}\"")
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
    buildFeatures {
        buildConfig = true
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
    implementation(libs.androidx.splashscreen)

    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}

fun loadApiKey(): String {
    val file = rootProject.file("keys.properties")
    return Properties()
        .apply { load(file.inputStream()) }
        .getProperty("API_KEY")
}