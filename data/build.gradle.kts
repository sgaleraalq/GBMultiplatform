plugins {
    id("com.gbmultiplatform.library")
    id("com.gbmultiplatform.firebase")
    id("com.gbmultiplatform.koin")
}

android {
    namespace = "com.gbmultiplatform.data"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.domain)
            api(libs.datastore)
            api(libs.datastore.preferences)
        }
    }
}
