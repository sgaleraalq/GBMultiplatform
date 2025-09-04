package com.gbmultiplatform.domain.utils

import androidx.compose.ui.graphics.ImageBitmap

data class ImagePath(
    val path: String
)

expect class SharedImage {
    var path: String
    fun toByteArray(): ByteArray?
    fun toImageBitmap(): ImageBitmap?
}
