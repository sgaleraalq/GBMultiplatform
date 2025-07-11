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
            id = "com.gbmultiplatform.kmp"
            implementationClass = "com.gbmultiplatform.convention.kmp.KotlinMultiplatformConventionPlugin"
        }
        register("kmpAppMultiplatform") {
            id = "com.gbmultiplatform.kmp.app.multiplatform"
            implementationClass = "com.gbmultiplatform.convention.kmp.KmpAppConventionPlugin"
        }
        register("kmpLibraryMultiplatform") {
            id = "com.gbmultiplatform.kmp.library.multiplatform"
            implementationClass = "com.gbmultiplatform.convention.kmp.KmpLibraryConventionPlugin"
        }
    }
}