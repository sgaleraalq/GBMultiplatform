plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.design_system"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}