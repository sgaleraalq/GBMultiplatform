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

package com.gbmultiplatform.convention.dependencies

import com.gbmultiplatform.convention.utils.AndroidConfiguration
import com.gbmultiplatform.convention.utils.AndroidConfiguration.ANDROID_FIREBASE_AUTH
import com.gbmultiplatform.convention.utils.AndroidConfiguration.ANDROID_FIREBASE_FIRESTORE
import com.gbmultiplatform.convention.utils.AndroidConfiguration.ANDROID_FIREBASE_STORAGE
import com.gbmultiplatform.convention.utils.AndroidConfiguration.FIREBASE_BOM
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

        with(pluginManager) {
//            apply("org.jetbrains.kotlin.native.cocoapods")
        }

        extensions.configure<KotlinMultiplatformExtension> {
            configureFirebaseAndroid(libs)
            configureFirebaseIos()
        }
    }
}

internal fun KotlinMultiplatformExtension.configureFirebaseAndroid(
    libs: VersionCatalog
){
    val firebaseBom = libs.findLibrary(FIREBASE_BOM).get()
    sourceSets.androidMain.dependencies {
        implementation(project.dependencies.platform(firebaseBom))
        implementation(libs.findLibrary(ANDROID_FIREBASE_AUTH).get())
        implementation(libs.findLibrary(ANDROID_FIREBASE_STORAGE).get())
        implementation(libs.findLibrary(ANDROID_FIREBASE_FIRESTORE).get())
    }
}

internal fun KotlinMultiplatformExtension.configureFirebaseIos() {

}
