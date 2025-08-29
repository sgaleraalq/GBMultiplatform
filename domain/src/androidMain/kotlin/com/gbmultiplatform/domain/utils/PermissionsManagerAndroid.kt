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

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.content.ContextCompat.checkSelfPermission
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionStatus
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionStatus.*
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionType

class PermissionsManagerAndroid(
    private val context: Context,
//    private val callback: PermissionCallback
) : IPermissionHandler {
    override fun askPermission(permissionType: PermissionType): PermissionStatus {
        val permissions = when(permissionType) {
            PermissionType.CAMERA -> listOf(CAMERA)
            PermissionType.MEDIA_FILES -> listOf(READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE)
        }

        val notGrantedPermissions = permissions.filter {
            !isPermissionGranted(it)
        }

//        notGrantedPermissions.forEach { permission ->
//            // ask for permissions
//
//        }
        // TODO
        return if (notGrantedPermissions.isEmpty()) {
            GRANTED
        } else {
            DENIED
        }
    }

    override fun isPermissionGranted(permission: String) =
        checkSelfPermission(context, permission) == PERMISSION_GRANTED


}
