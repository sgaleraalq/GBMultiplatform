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

package com.gbmultiplatform.convention.utils

import com.gbmultiplatform.ProjectConfiguration.COCOA_DEPLOYMENT_TARGET
import com.gbmultiplatform.ProjectConfiguration.COCOA_HOMEPAGE
import com.gbmultiplatform.ProjectConfiguration.COCOA_SUMMARY
import com.gbmultiplatform.ProjectConfiguration.COCOA_VERSION
import com.gbmultiplatform.ProjectConfiguration.IOS_BASENAME
import com.gbmultiplatform.ProjectConfiguration.IOS_STATIC
import com.gbmultiplatform.convention.utils.AndroidConfiguration.ACTIVITY_COMPOSE
import com.gbmultiplatform.convention.utils.AndroidConfiguration.COMPOSE_PREVIEW
import com.gbmultiplatform.convention.utils.KmpConfiguration.COMPOSE_FOUNDATION
import com.gbmultiplatform.convention.utils.KmpConfiguration.COMPOSE_MATERIAL3
import com.gbmultiplatform.convention.utils.KmpConfiguration.COMPOSE_RUNTIME
import com.gbmultiplatform.convention.utils.KmpConfiguration.COMPOSE_UI
import com.gbmultiplatform.convention.utils.KmpConfiguration.COMPOSE_UI_TOOLING_PREVIEW
import com.gbmultiplatform.convention.utils.KmpConfiguration.KOTLIN_TEST
import com.gbmultiplatform.convention.utils.KmpConfiguration.LIFECYCLE_RUNTIME_COMPOSE
import com.gbmultiplatform.convention.utils.KmpConfiguration.LIFECYCLE_VIEWMODEL
import org.gradle.api.artifacts.VersionCatalog
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension

internal fun KotlinMultiplatformExtension.configureCommonDependencies(
    libs: VersionCatalog
) {
    sourceSets.commonMain.dependencies {
        implementation(libs.findLibrary(COMPOSE_RUNTIME).get())
        implementation(libs.findLibrary(COMPOSE_FOUNDATION).get())
        implementation(libs.findLibrary(COMPOSE_MATERIAL3).get())
        implementation(libs.findLibrary(COMPOSE_UI).get())
        implementation(libs.findLibrary(COMPOSE_UI_TOOLING_PREVIEW).get())
        implementation(libs.findLibrary(LIFECYCLE_VIEWMODEL).get())
        implementation(libs.findLibrary(LIFECYCLE_RUNTIME_COMPOSE).get())
    }

    sourceSets.commonTest.dependencies {
        implementation(libs.findLibrary(KOTLIN_TEST).get())
    }
}

internal fun KotlinMultiplatformExtension.configureAndroidKmp(
    libs: VersionCatalog
) {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JVM_11)
        }
    }
    sourceSets.androidMain.dependencies {
        implementation(libs.findLibrary(ACTIVITY_COMPOSE).get())
        implementation(libs.findLibrary(COMPOSE_PREVIEW).get())
    }
}

internal fun KotlinMultiplatformExtension.configureCocoapods(
    cocoapods: CocoapodsExtension
) {
    with(cocoapods) {
        version = COCOA_VERSION
        summary = COCOA_SUMMARY
        homepage = COCOA_HOMEPAGE
        ios.deploymentTarget = COCOA_DEPLOYMENT_TARGET
        framework {
            baseName = IOS_BASENAME
            isStatic = IOS_STATIC
        }
    }
}

internal fun KotlinMultiplatformExtension.configureiOSSimulators() {
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}
