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

import kotlinx.serialization.Serializable

@Serializable
sealed interface CommonImage {
    val uri: String

    @Serializable
    data class FromFrontCamera(override val uri: String) : CommonImage

    @Serializable
    data class FromBackCamera(override val uri: String) : CommonImage

    @Serializable
    data class FromGallery(override val uri: String, val mimeType: String? = null) : CommonImage
}

interface ImageLoader {
    suspend fun loadImage(
        uri: String,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int,
        isFrontCamera: Boolean
    ): ByteArray?
}

class SharedImagesBridge {
    private var imageLoader: ImageLoader? = null

    fun setListener(imageLoader: ImageLoader) {
        this.imageLoader = imageLoader
    }

    suspend fun loadImage(
        uri: String,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int,
        isFrontCamera: Boolean
    ): ByteArray? {
        return imageLoader?.loadImage(
            uri,
            maxWidth,
            maxHeight,
            quality,
            isFrontCamera
        ) ?: error("ImageLoader not set")
    }
}
