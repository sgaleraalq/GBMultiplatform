plugins {
    id("com.gbmultiplatform.android.app")
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

sourceSets {
    tasks.register("testClasses")
}

dependencies {
    implementation(platform(libs.android.firebase.bom))
    implementation(libs.android.firebase.analytics)

    implementation(libs.androidx.appcompat)
    implementation(projects.core.di)
    implementation(projects.core.presentation)
    implementation(projects.data)
    implementation(projects.domain)
}
