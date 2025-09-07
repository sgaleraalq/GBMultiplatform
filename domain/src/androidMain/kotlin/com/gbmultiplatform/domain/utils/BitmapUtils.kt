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

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import androidx.exifinterface.media.ExifInterface.ORIENTATION_NORMAL
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_180
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_270
import androidx.exifinterface.media.ExifInterface.ORIENTATION_ROTATE_90
import androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION

object BitmapUtils {

    /**
     * Loads a scaled Bitmap from a Uri while keeping EXIF rotation.
     * @param uri Image Uri
     * @param contentResolver ContentResolver
     * @param maxSize Maximum width or height of the resulting Bitmap
     */
    fun getBitmapFromUri(uri: Uri, contentResolver: ContentResolver, maxSize: Int = 1024): Bitmap? {
        try {
            // 1. Decode only the image dimensions
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, options)
            }

            // 2. Calculate inSampleSize to scale the image
            val scale = maxOf(options.outWidth, options.outHeight) / maxSize.toFloat()
            val sampleSize = scale.coerceAtLeast(1f).toInt()

            // 3. Decode the scaled bitmap
            val bitmap = contentResolver.openInputStream(uri)?.use { stream ->
                val decodeOptions = BitmapFactory.Options().apply { inSampleSize = sampleSize }
                BitmapFactory.decodeStream(stream, null, decodeOptions)
            } ?: return null

            // 4. Read the EXIF orientation
            val rotationDegrees = contentResolver.openInputStream(uri)?.use { stream ->
                val exif = ExifInterface(stream)
                when (exif.getAttributeInt(TAG_ORIENTATION, ORIENTATION_NORMAL)) {
                    ORIENTATION_ROTATE_90 -> 90f
                    ORIENTATION_ROTATE_180 -> 180f
                    ORIENTATION_ROTATE_270 -> 270f
                    else -> 0f
                }
            } ?: 0f

            // 5. Apply rotation if needed
            return if (rotationDegrees != 0f) {
                val matrix = Matrix().apply { postRotate(rotationDegrees) }
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            } else {
                bitmap
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
