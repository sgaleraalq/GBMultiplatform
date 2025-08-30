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
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionStatus.GRANTED
import com.gbmultiplatform.domain.utils.IPermissionHandler.PermissionType
import kotlinx.coroutines.CancellableContinuation

class PermissionsManagerAndroid() : IPermissionHandler {

    private var continuation: CancellableContinuation<PermissionStatus>? = null

    override suspend fun askPermission(
        permissionType: PermissionType
    ): PermissionStatus = GRANTED
//        suspendCancellableCoroutine { cont ->
//            val permission = when (permissionType) {
//                PermissionType.CAMERA -> CAMERA
//                PermissionType.MEDIA_FILES -> READ_EXTERNAL_STORAGE
//            }
//            if (isPermissionGranted(permission)) {
//                cont.resume(GRANTED)
//                continuation = null
//            } else {
//                launcher.launch(permission)
//            }
//        }

    override fun isPermissionGranted(permission: String) = true
//        checkSelfPermission(activity, permission) == PERMISSION_GRANTED
}
