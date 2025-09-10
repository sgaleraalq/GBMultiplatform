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

package com.gbmultiplatform.domain.utils.camera

import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.gbmultiplatform.domain.utils.takePhoto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GBCamera(
    modifier: Modifier = Modifier,
    uri: Uri,
    controller: LifecycleCameraController,
    changeCamera: () -> CameraSelector,
    onPhotoTaken: (Uri) -> Unit,
    closeCamera: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        GBCameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize()
        )

        IconButton(
            modifier = Modifier.offset(16.dp, 16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = White
            ),
            onClick = {
                controller.cameraSelector = changeCamera()
            }
        ) {
            Icon(
                imageVector = Default.Cameraswitch,
                contentDescription = "Switch camera"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(BottomCenter)
                .padding(16.dp),
            contentAlignment = Center
        ) {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = White
                ),
                onClick = {
                    takePhoto(
                        uri = uri,
                        controller = controller,
                        onPhotoTaken = { onPhotoTaken(it) },
                        context = context,
                        closeCamera = { closeCamera() }
                    )
                }
            ) {
                Icon(
                    imageVector = Default.Camera,
                    contentDescription = "Take picture"
                )
            }
        }
    }
}

@Composable
fun GBCameraPreview(
    modifier: Modifier = Modifier,
    controller: LifecycleCameraController
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier,
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        }
    )
}
