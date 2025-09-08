plugins {
    id("com.gbmultiplatform.library")
    id("com.gbmultiplatform.koin")
}

android {
    namespace = "com.gbmultiplatform.domain"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.exifinterface)
            implementation(libs.androidx.camera.core)
            implementation(libs.androidx.camera.compose)
            implementation(libs.androidx.camera.lifecycle)
            implementation(libs.androidx.camera.camera2)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
