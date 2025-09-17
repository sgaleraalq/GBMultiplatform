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
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat.JPEG
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.decodeStream
import android.graphics.Matrix
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.net.toUri
import androidx.exifinterface.media.ExifInterface
import androidx.exifinterface.media.ExifInterface.ORIENTATION_NORMAL
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_180
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_270
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_90
import androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION
import com.gbmultiplatform.App
import com.gbmultiplatform.domain.utils.ImageLoader
import com.gbmultiplatform.domain.utils.PermissionBridge
import com.gbmultiplatform.domain.utils.PermissionResultCallback
import com.gbmultiplatform.domain.utils.PermissionType
import com.gbmultiplatform.domain.utils.PermissionType.GALLERY
import com.gbmultiplatform.domain.utils.PermissionsBridgeListener
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext
import java.io.ByteArrayOutputStream

open class GBMultiplatformActivity :
    AppCompatActivity(),
    PermissionsBridgeListener,
    ImageLoader
{
    private var callback: PermissionResultCallback? = null
    private var pendingPermission: String? = null
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(RequestPermission()) { isGranted ->
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
    private val requestMultiplePermissions =
        registerForActivityResult(RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.all { it.value }
            if (allGranted) {
                callback?.onPermissionsGranted()
            } else {
                val permanentlyDenied = permissions.any { entry ->
                    val permission = entry.key
                    !entry.value && !shouldShowRequestPermissionRationale(permission)
                }
                callback?.onPermissionsDenied(permanentlyDenied)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        GlobalContext.get().get<PermissionBridge>().setListener(this)
        GlobalContext.get().get<SharedImagesBridge>().setListener(this)
        setContent {
            App()
        }
    }

    override fun requestPermission(
        permission: PermissionType,
        callback: PermissionResultCallback
    ) {
        when (permission) {
            PermissionType.CAMERA -> handleSinglePermissions(permission, callback)
            GALLERY -> handleMultiplePermissions(permission, callback)
        }
    }

    override fun isPermissionsGranted(permission: PermissionType): Boolean {
        return when (permission) {
            PermissionType.CAMERA -> isPermissionsGranted(CAMERA)
            else -> true
        }
    }

    private fun handleSinglePermissions(
        permission: PermissionType,
        callback: PermissionResultCallback
    ) {
        val androidPermission = when (permission) {
            PermissionType.CAMERA -> CAMERA
            else -> error("Unknown permission type") /* should not get here */
        }

        when {
            isPermissionsGranted(permission) -> {
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

    private fun handleMultiplePermissions(
        permission: PermissionType,
        callback: PermissionResultCallback
    ) {
        when {
            areMultiplePermissionsGranted(permission) -> {
                callback.onPermissionsGranted()
            }

            shouldShowRationaleForMultiplePermissions(permission) -> {
                callback.onPermissionsDenied(false)
            }

            else -> {
                this.callback = callback
                requestMediaPermissions()
            }
        }
    }

    private fun isPermissionsGranted(permission: String) =
        checkSelfPermission(this, permission) == PERMISSION_GRANTED

    private fun areMultiplePermissionsGranted(permission: PermissionType): Boolean {
        val permissions: Array<String> = when (permission) {
            GALLERY -> getMediaPermissions()
            else -> arrayOf() /* should not get here */
        }
        return arePermissionsGranted(permissions)
    }

    private fun arePermissionsGranted(permissions: Array<String>) =
        permissions.all { isPermissionsGranted(it) }

    private fun shouldShowRationaleForMultiplePermissions(permission: PermissionType): Boolean {
        return when (permission) {
            GALLERY -> shouldShowRationaleForMediaPermissions()
            else -> false /* should not get here */
        }
    }


    /**
     * Specific permissions required for the user
     */
    private fun getMediaPermissions(): Array<String> =
        when {
            SDK_INT >= UPSIDE_DOWN_CAKE -> arrayOf(
                READ_MEDIA_IMAGES,
                READ_MEDIA_VISUAL_USER_SELECTED
            )

            SDK_INT >= TIRAMISU -> arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO)
            else -> arrayOf(READ_EXTERNAL_STORAGE)
        }

    private fun shouldShowRationaleForMediaPermissions(): Boolean {
        return if (SDK_INT >= UPSIDE_DOWN_CAKE) {
            shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES) ||
                    shouldShowRequestPermissionRationale(READ_MEDIA_VISUAL_USER_SELECTED)
        } else if (SDK_INT >= TIRAMISU) {
            shouldShowRequestPermissionRationale(READ_MEDIA_IMAGES)
        } else {
            shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)
        }
    }

    private fun requestMediaPermissions() {
        requestMultiplePermissions.launch(getMediaPermissions())
    }

    /**
     * Image loader
     */
    override suspend fun loadImage(
        uri: String,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int,
        isFrontCamera: Boolean
    ): ByteArray? = withContext(Dispatchers.IO) {
        val androidUri = uri.toUri()

        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        contentResolver.openInputStream(androidUri)?.use {
            decodeStream(it, null, bounds)
        }
        val srcW = bounds.outWidth
        val srcH = bounds.outHeight
        if (srcW <= 0 || srcH <= 0) return@withContext null

        var sample = 1
        while (srcW / (sample * 2) >= maxWidth && srcH / (sample * 2) >= maxHeight) {
            sample *= 2
        }

        val opts = BitmapFactory.Options().apply {
            inSampleSize = sample
            inPreferredConfig = Bitmap.Config.RGB_565
        }

        val bmp = contentResolver.openInputStream(androidUri)?.use {
            decodeStream(it, null, opts)
        } ?: return@withContext null

        // 4. Leer EXIF
        val exif = contentResolver.openInputStream(androidUri)?.use { ExifInterface(it) }
        val rotation = when (exif?.getAttributeInt(TAG_ORIENTATION, ORIENTATION_NORMAL)) {
            ORIENTATION_ROTATE_90 -> 90
            ORIENTATION_ROTATE_180 -> 180
            ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }

        val matrix = Matrix().apply {
            if (isFrontCamera) postScale(-1f, 1f, bmp.width / 2f, bmp.height / 2f)
            val adjustedRotation = if (isFrontCamera) 90 else rotation
            if (adjustedRotation != 0) postRotate(adjustedRotation.toFloat())

        }

        val finalBmp = createBitmap(bmp, 0, 0, bmp.width, bmp.height, matrix, true)

        ByteArrayOutputStream().use { out ->
            finalBmp.compress(JPEG, quality.coerceIn(1, 100), out)
            out.toByteArray()
        }
    }
}

