plugins {
    `kotlin-dsl`
}

group = "com.gbmultiplatform.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        /**
         * Android
         */
        register("androidApplication") {
            id = "com.gbmultiplatform.android.app"
            implementationClass = "com.gbmultiplatform.convention.android.AndroidAppConventionPlugin"
        }
        register("androidFirebase") {
            id = "com.gbmultiplatform.android.firebase"
            implementationClass = "com.gbmultiplatform.convention.android.AndroidFirebaseConventionPlugin"
        }

        /**
         * Kotlin Multiplatform
         */
        register("kotlinMultiplatform") {
            id = "com.gbmultiplatform"
            implementationClass = "com.gbmultiplatform.convention.kmp.KotlinMultiplatformConventionPlugin"
        }
        register("kmpAppMultiplatform") {
            id = "com.gbmultiplatform.app"
            implementationClass = "com.gbmultiplatform.convention.kmp.KmpAppConventionPlugin"
        }
        register("kmpLibraryMultiplatform") {
            id = "com.gbmultiplatform.library"
            implementationClass = "com.gbmultiplatform.convention.kmp.KmpLibraryConventionPlugin"
        }

        /**
         * Dependencies
         */
        register("koinConvention") {
            id = "com.gbmultiplatform.koin"
            implementationClass = "com.gbmultiplatform.convention.kmp.dependencies.KoinConventionPlugin"
        }

        register("firebaseConvention") {
            id = "com.gbmultiplatform.firebase"
            implementationClass = "com.gbmultiplatform.convention.kmp.dependencies.FirebaseConventionPlugin"
        }
    }
}