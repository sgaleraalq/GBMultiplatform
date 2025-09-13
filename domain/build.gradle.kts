plugins {
    id("com.gbmultiplatform.library")
    id("com.gbmultiplatform.koin")
    id("com.gbmultiplatform.serialization")
}

android {
    namespace = "com.gbmultiplatform.domain"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.camera.core)
            implementation(libs.androidx.camera.camera2)
            implementation(libs.androidx.camera.lifecycle)
            implementation(libs.androidx.camera.view)
            implementation(libs.androidx.camera.extensions)
            implementation(libs.androidx.material.icons.extended)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
