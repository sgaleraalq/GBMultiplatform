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

package com.gbmultiplatform

object ProjectConfiguration {

    const val NAMESPACE = "com.gbmultiplatform"
    const val APPLICATION_ID = "com.gbmultiplatform"
    const val VERSION_CODE = 20250619
    const val VERSION_NAME = "1.0.0"
    const val COMPILE_SDK = 35
    const val MIN_SDK = 24
    const val TARGET_SDK = 35

    /**
     * Cocoapods
     */
    const val IOS_BASENAME = "ComposeApp"
    const val IOS_STATIC = true
    const val COCOA_VERSION = VERSION_NAME
    const val COCOA_SUMMARY = "GB Multiplatform"
    const val COCOA_HOMEPAGE = "https://www.sergiogalera.dev/"
    const val COCOA_DEPLOYMENT_TARGET = "18.2"
}
