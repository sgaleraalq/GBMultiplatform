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
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.checkSelfPermission
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionsManagerAndroid(
    private val context: Context,
    private val activity: ComponentActivity
) :
    IPermissionHandler {

    private var continuation: CancellableContinuation<PermissionStatus>? = null
    private var launcher: ActivityResultLauncher<String> =
        activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            continuation?.resume(if (isGranted) PermissionStatus.GRANTED else PermissionStatus.DENIED)
            continuation = null
        }

    override suspend fun askPermission(
        permissionType: PermissionType
    ): PermissionStatus =
        suspendCancellableCoroutine { cont ->
            val permission = when (permissionType) {
                PermissionType.CAMERA -> CAMERA
                PermissionType.MEDIA_FILES -> READ_EXTERNAL_STORAGE
            }

            val isGranted = isPermissionGranted(permission)
            if (isGranted) {
                cont.resume(PermissionStatus.GRANTED)
                continuation = null
            } else {
                launcher.launch(permission)
            }
        }

    override fun isPermissionGranted(permission: String) =
        checkSelfPermission(context, permission) == PERMISSION_GRANTED
}
