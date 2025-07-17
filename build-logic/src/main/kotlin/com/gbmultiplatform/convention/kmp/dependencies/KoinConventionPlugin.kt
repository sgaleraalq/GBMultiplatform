/*
 * Designed and developed by 2025 sgaleraalq (Sergio Galera)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gbmultiplatform.convention.kmp.dependencies

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
