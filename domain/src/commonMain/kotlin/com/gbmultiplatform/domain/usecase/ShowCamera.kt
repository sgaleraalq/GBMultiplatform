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

package com.gbmultiplatform.domain.usecase

import com.gbmultiplatform.domain.utils.PermissionBridge
import com.gbmultiplatform.domain.utils.PermissionResultCallback
import com.gbmultiplatform.domain.utils.PermissionType.CAMERA

class ShowCamera(
    private val permissionBridge: PermissionBridge
) {
    operator fun invoke(
        onLaunchCamera: () -> Unit,
        onPermissionsDenied: () -> Unit
    ) {
        requestCameraPermission(
            initCamera = { onLaunchCamera() },
            onPermissionsDenied = { onPermissionsDenied() }
        )
    }

    private fun requestCameraPermission(
        initCamera: () -> Unit,
        onPermissionsDenied: () -> Unit
    ) {
        permissionBridge
            .requestPermission(
                permission = CAMERA,
                callback = object : PermissionResultCallback {
                    override fun onPermissionsGranted() {
                        permissionBridge.isPermissionGranted(CAMERA)
                        initCamera()
                    }

                    override fun onPermissionsDenied(isPermanentDenied: Boolean) {
                        onPermissionsDenied()
                    }
                }
            )
    }
}
