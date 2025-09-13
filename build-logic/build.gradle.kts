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

/**
 * Kotlin Multiplatform
 */
gradlePlugin {
    plugins {
        register("kmpLibraryMultiplatform") {
            id = "com.gbmultiplatform.library"
            implementationClass = "com.gbmultiplatform.convention.KmpLibraryConventionPlugin"
        }

        /**
         * Dependencies
         */
        register("serialization") {
            id = "com.gbmultiplatform.serialization"
            implementationClass = "com.gbmultiplatform.convention.dependencies.SerializationConventionPlugin"
        }
        register("koinConvention") {
            id = "com.gbmultiplatform.koin"
            implementationClass = "com.gbmultiplatform.convention.dependencies.KoinConventionPlugin"
        }
        register("firebaseConvention") {
            id = "com.gbmultiplatform.firebase"
            implementationClass = "com.gbmultiplatform.convention.dependencies.FirebaseConventionPlugin"
        }
    }
}

/**
 * Android
 */
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.gbmultiplatform.android.app"
            implementationClass = "com.gbmultiplatform.convention.AndroidAppConventionPlugin"
        }
    }
}

