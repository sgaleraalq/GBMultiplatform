plugins {
    id("com.gbmultiplatform.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.gbmultiplatform.presentation"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.navigation.compose)
        }
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.communication)
            implementation(projects.core.designSystem)
            implementation(compose.components.resources)
            api(libs.kotlinx.serialization.json)
        }
    }
}