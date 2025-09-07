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

import android.net.Uri.EMPTY
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.gbmultiplatform.domain.utils.BitmapUtils.getBitmapFromUri
import com.gbmultiplatform.domain.utils.ComposeFileProvider.Companion.getImageUri

// CameraManager.android.kt
@Composable
actual fun rememberCameraManager(
    onResult: (SharedImage?) -> Unit
): CameraManager {
    var tempPhotoUri by remember { mutableStateOf(value = EMPTY) }

    val context = LocalContext.current
    val contentResolver = context.contentResolver

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = TakePicture(),
        onResult = { success ->
            if (success) {
                val sharedImage = SharedImage(getBitmapFromUri(tempPhotoUri, contentResolver))
                onResult.invoke(sharedImage)
            } else {
                onResult.invoke(null)
            }
        }
    )

    return remember {
        CameraManager(
            onLaunch = {
                tempPhotoUri = getImageUri(context)
                cameraLauncher.launch(tempPhotoUri)
            }
        )
    }
}

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}
