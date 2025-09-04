plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.domain"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(projects.app)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}
