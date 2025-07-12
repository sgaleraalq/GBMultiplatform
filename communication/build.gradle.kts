 plugins {
     id("com.gbmultiplatform.kmp.library")
     id("com.google.gms.google-services")
     id("kotlinx-serialization")
 }

android {
    namespace = "com.gbmultiplatform.communication"
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(project.dependencies.platform(libs.android.firebase.bom))
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            api(libs.gitlive.firebase.firestore)
        }
    }
}