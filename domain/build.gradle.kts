plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.domain"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.exifinterface)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
