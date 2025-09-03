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

package com.gbmultiplatform.presentation

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import com.gbmultiplatform.App
import com.gbmultiplatform.domain.utils.CameraBridge
import com.gbmultiplatform.domain.utils.PermissionBridge
import com.gbmultiplatform.domain.utils.PermissionResultCallback
import com.gbmultiplatform.domain.utils.PermissionType
import com.gbmultiplatform.domain.utils.PermissionsBridgeListener
import org.koin.core.context.GlobalContext

open class GBMultiplatformActivity :
    AppCompatActivity(), PermissionsBridgeListener
{

    private var callback: PermissionResultCallback? = null
    private var pendingPermission: String? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        GlobalContext.get().get<PermissionBridge>().setListener(this)
//        GlobalContext.get().get<CameraBridge>().setListener(this)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                val permission = pendingPermission
                if (permission != null) {
                    if (isGranted) {
                        callback?.onPermissionsGranted()
                    } else {
                        val permanentlyDenied = !shouldShowRequestPermissionRationale(permission)
                        callback?.onPermissionsDenied(permanentlyDenied)
                    }
                }
                pendingPermission = null
                callback = null
            }

        setContent {
            App()
        }
    }

    override fun requestPermission(
        permission: PermissionType,
        callback: PermissionResultCallback
    ) {
        val androidPermission = when (permission) {
            PermissionType.CAMERA -> CAMERA
            PermissionType.MEDIA_FILES -> READ_EXTERNAL_STORAGE
        }

        when {
            isPermissionsGranted(androidPermission) -> {
                callback.onPermissionsGranted()
            }

            shouldShowRequestPermissionRationale(androidPermission) -> {
                callback.onPermissionsDenied(false)
            }

            else -> {
                this.callback = callback
                this.pendingPermission = androidPermission
                requestPermissionLauncher.launch(androidPermission)
            }
        }
    }

    override fun isPermissionsGranted(permission: PermissionType): Boolean {
        return when (permission) {
            PermissionType.CAMERA -> isPermissionsGranted(CAMERA)
            PermissionType.MEDIA_FILES -> isPermissionsGranted(READ_EXTERNAL_STORAGE)
        }
    }

    private fun isPermissionsGranted(permission: String): Boolean {
        return checkSelfPermission(
            this,
            permission
        ) == PERMISSION_GRANTED
    }
}

