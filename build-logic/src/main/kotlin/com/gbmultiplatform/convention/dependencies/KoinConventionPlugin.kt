package com.gbmultiplatform.convention.dependencies

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KoinConventionPlugin : Plugin<Project> {

    companion object {
        const val KOIN_BOM = "koin.bom"
        const val KOIN_CORE = "koin.core"
        const val KOIN_COMPOSE = "koin.compose"
        const val KOIN_COMPOSE_VIEWMODEL = "koin.compose.viewmodel"
        const val KOIN_ANDROID = "koin.android"
    }

    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.commonMain.dependencies {
                api(project.dependencies.platform(libs.findLibrary(KOIN_BOM).get()))
                api(libs.findLibrary(KOIN_CORE).get())
                api(libs.findLibrary(KOIN_COMPOSE).get())
                api(libs.findLibrary(KOIN_COMPOSE_VIEWMODEL).get())
            }
            sourceSets.androidMain.dependencies {
                api(libs.findLibrary(KOIN_ANDROID).get())
            }
        }
    }
}