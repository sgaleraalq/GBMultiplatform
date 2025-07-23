plugins {
    id("com.gbmultiplatform.library")
}

android {
    namespace = "com.gbmultiplatform.design_system"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(libs.ktor.client.android)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
            api(compose.materialIconsExtended)


            /**
             * Images from network
             */
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
        }
    }
}