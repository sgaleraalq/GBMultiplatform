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
         * Kotlin Multiplatform
         */
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
        register("firebaseConvention") {
            id = "com.gbmultiplatform.firebase"
            implementationClass =
                "com.gbmultiplatform.convention.dependencies.FirebaseConventionPlugin"
        }
    }
}