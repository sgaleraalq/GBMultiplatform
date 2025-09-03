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

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.gbmultiplatform.R.xml.provider_paths
import java.io.File
import java.util.Objects.requireNonNull

class ComposeFileProvider: FileProvider(provider_paths) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val tempFile = File.createTempFile(
                "picture_${System.currentTimeMillis()}", ".png", context.cacheDir
            ).apply { createNewFile() }
            val authority = context.applicationContext.packageName + ".provider"

            return getUriForFile(requireNonNull(context), authority, tempFile,)
        }
    }
}