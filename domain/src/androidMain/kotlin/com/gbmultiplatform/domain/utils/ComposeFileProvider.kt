package com.gbmultiplatform.domain.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.gbmultiplatform.domain.R
import java.io.File
import java.lang.System.currentTimeMillis
import java.util.Objects

/**
 * FileProvider returns a content URI for a file to be written
 */
class ComposeFileProvider : FileProvider(R.xml.provider_paths) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val authority = context.applicationContext.packageName + ".provider"
            val imagesDir = File(context.cacheDir, "images").apply { mkdirs() }

            val file = File.createTempFile(
                "img_${currentTimeMillis()}",
                ".jpg",
                imagesDir
            )

            return getUriForFile(Objects.requireNonNull(context), authority, file)
        }
    }
}