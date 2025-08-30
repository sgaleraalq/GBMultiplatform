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

package com.gbmultiplatform.domain.utils

import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionStatus
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionType

interface PermissionCallback {
    fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus)
}

interface IPermissionHandler {
    enum class PermissionStatus {
        GRANTED, DENIED
    }

    enum class PermissionType {
        CAMERA, MEDIA_FILES
    }

    suspend fun askPermission(permissionType: PermissionType): PermissionStatus
    fun isPermissionGranted(permission: String): Boolean
//    fun isPermissionDeniedForever(permission: String): Boolean
}
