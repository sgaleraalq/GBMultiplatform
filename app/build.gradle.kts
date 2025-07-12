plugins {
    id("com.android.application")

    id("com.gbmultiplatform.app")
    id("com.gbmultiplatform.firebase")
}

android {
    namespace = "com.gbmultiplatform"

    defaultConfig {
        applicationId = "com.gbmultiplatform"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
    }
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.androidx.core.ktx)
        }
        commonMain.dependencies {
            implementation(projects.communication)
            implementation(projects.core.di)
            implementation(compose.components.resources)
        }
    }
}
