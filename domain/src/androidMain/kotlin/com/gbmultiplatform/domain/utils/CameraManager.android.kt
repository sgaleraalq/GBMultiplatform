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

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.CameraSelector.DEFAULT_FRONT_CAMERA
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OutputFileOptions.Builder
import androidx.camera.core.ImageCapture.OutputFileResults
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController.IMAGE_CAPTURE
import androidx.camera.view.LifecycleCameraController
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getMainExecutor
import com.gbmultiplatform.domain.utils.FileProvider.Companion.getImageUri
import com.gbmultiplatform.domain.utils.camera.GBCamera

// CameraManager.android.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun CameraManagerCompose(
    onResult: (CommonImage?) -> Unit
) {
    val context = LocalContext.current

    val photoUri by remember { mutableStateOf(getImageUri(context)) }
    val controller = remember {
        LifecycleCameraController(context).apply { setEnabledUseCases(IMAGE_CAPTURE) }
    }

    fun isBackCamera(): Boolean {
        return controller.cameraSelector == DEFAULT_BACK_CAMERA
    }

    fun changeCamera(): CameraSelector {
        return if (isBackCamera()) {
            DEFAULT_FRONT_CAMERA
        } else {
            DEFAULT_BACK_CAMERA
        }
    }

    GBCamera(
        uri = photoUri,
        controller = controller,
        changeCamera = { changeCamera() },
        onPhotoTaken = { uri ->
            onResult(CommonImage(uri.toString()))
        }
    )
}

internal fun takePhoto(
    uri: Uri,
    context: Context,
    controller: LifecycleCameraController,
    onPhotoTaken: (Uri) -> Unit,
) {
    val output = Builder(
        context.contentResolver,
        uri,
        ContentValues()
    ).build()

    controller.takePicture(
        output,
        getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: OutputFileResults) {
                onPhotoTaken(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("Camera", "Couldn't save photo: ", exception)
            }
        }
    )
}
