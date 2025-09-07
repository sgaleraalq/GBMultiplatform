package com.gbmultiplatform.domain.utils

data class CommonImage(
    val uri: String,
    val mimeType: String? = null
)

interface ImageLoader {
    suspend fun loadImage(
        uri: String,
        maxWidth: Int,
        maxHeight: Int,
        quality: Int = 85
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
        quality: Int = 85
    ): ByteArray? {
        return imageLoader?.loadImage(
            uri,
            maxWidth,
            maxHeight,
            quality
        ) ?: error("ImageLoader not set")
    }
}
