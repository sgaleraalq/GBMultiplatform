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

package com.gbmultiplatform.presentation.screens.insert_player.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import com.gbmultiplatform.design_system.components.GBImageBoxRequester
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.body_image
import gbmultiplatform.core.presentation.generated.resources.face_image
import gbmultiplatform.core.presentation.generated.resources.images
import org.jetbrains.compose.resources.stringResource

@Composable
fun InsertPlayerImages(
    faceImg: ByteArray?,
    bodyImg: ByteArray?,
    onFaceClicked: () -> Unit,
    onBodyClicked: () -> Unit,
    showMediaOrCamera: () -> Unit
) {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.images),
        style = gBTypography().titleMedium,
        alignment = Start
    )
    GBImageBoxRequester(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.face_image),
        image = faceImg,
        onClick = {
            onFaceClicked()
            showMediaOrCamera()
        }
    )
    GBImageBoxRequester(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.body_image),
        image = bodyImg,
        onClick = {
            onBodyClicked()
            showMediaOrCamera()
        }
    )
}