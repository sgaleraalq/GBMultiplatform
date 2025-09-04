plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.domain"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
