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
        register("kotlinMultiplatform") {
            id = "com.gbmultiplatform"
            implementationClass = "com.gbmultiplatform.convention.KotlinMultiplatformConventionPlugin"
        }
        register("kmpAppMultiplatform") {
            id = "com.gbmultiplatform.app"
            implementationClass = "com.gbmultiplatform.convention.KmpAppConventionPlugin"
        }
        register("kmpLibraryMultiplatform") {
            id = "com.gbmultiplatform.library"
            implementationClass = "com.gbmultiplatform.convention.KmpLibraryConventionPlugin"
        }

        /**
         * Dependencies
         */
        register("koinConvention") {
            id = "com.gbmultiplatform.koin"
            implementationClass = "com.gbmultiplatform.convention.dependencies.kmp.KoinConventionPlugin"
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
        register("androidFirebase") {
            id = "com.gbmultiplatform.android.firebase"
            implementationClass = "com.gbmultiplatform.convention.dependencies.android.AndroidFirebaseConventionPlugin"
        }
    }
}

/**
 * iOS
 */
gradlePlugin {
    plugins {

    }
}
