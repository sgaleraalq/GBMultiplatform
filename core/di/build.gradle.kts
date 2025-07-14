plugins {
    id("com.gbmultiplatform.library")
    id("com.gbmultiplatform.koin")
}

android {
    namespace = "com.gbmultiplatform.di"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.presentation)
        }
    }
}