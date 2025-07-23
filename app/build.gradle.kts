plugins {
    id("com.gbmultiplatform.android.app")
    id("com.gbmultiplatform.android.firebase")
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

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(projects.core.di)
    implementation(projects.core.presentation)
    implementation(projects.data)
}
