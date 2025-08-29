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
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionType

@Composable
actual fun createPermissionManager(callback: PermissionCallback): IPermissionHandler {
    val context = LocalContext.current
    return remember { PermissionsManager(context) }
}

class PermissionsManager(
    private val context: Context,
) : IPermissionHandler {
    override fun askPermission(permissionType: PermissionType) {
        println("Asking for permissions: $permissionType")
    }

    override fun isPermissionGranted(permissionType: PermissionType): Boolean {
        val permissions = when (permissionType) {
            PermissionType.CAMERA -> listOf(CAMERA)
        }

        return permissions.all { p ->
            ContextCompat.checkSelfPermission(context, p) == PERMISSION_GRANTED
        }
    }
}
