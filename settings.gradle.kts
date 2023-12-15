pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("appConfig") {
            version("compileSdk", "34")
            version("minSdk", "26")
            version("targetSdk", "34")
        }
    }
}

rootProject.name = "NBA Schedules"
include(":app")
include(":data:settings")
include(":feature:favorite")
include(":common:ui")
include(":data:teams")
include(":common:network")
include(":common:util")
include(":data:games")
include(":feature:teamGames")
include(":domain")
include(":feature:leagueGames")
