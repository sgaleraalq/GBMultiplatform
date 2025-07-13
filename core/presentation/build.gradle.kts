plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.presentation"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.androidx.core.ktx)
        }
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.communication)
            implementation(compose.components.resources)
        }
    }
}