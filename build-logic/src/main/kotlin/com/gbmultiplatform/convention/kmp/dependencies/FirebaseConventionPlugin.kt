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

class FirebaseConventionPlugin : Plugin<Project> {
    companion object {
        const val ANDROID_FIREBASE_BOM = "android.firebase.bom"
        const val GITLIVE_FIREBASE = "gitlive.firebase.firestore"
        const val KOTLIN_SERIALIZATION = "kotlinx.serialization.json"
    }

    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("kotlinx-serialization")
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.commonMain.dependencies {
                    implementation(libs.findLibrary(GITLIVE_FIREBASE).get())
                    implementation(libs.findLibrary(KOTLIN_SERIALIZATION).get())
                }

                sourceSets.androidMain.dependencies {
                    implementation(project.dependencies.platform(libs.findLibrary(ANDROID_FIREBASE_BOM).get()))
                }
            }
        }
    }
}
