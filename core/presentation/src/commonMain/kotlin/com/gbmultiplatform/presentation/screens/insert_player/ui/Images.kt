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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImageBoxRequester
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.face_image
import gbmultiplatform.core.presentation.generated.resources.images
import gbmultiplatform.core.presentation.generated.resources.use_same_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun InsertPlayerImages(
    faceImg: CommonImage?,
    bodyImg: CommonImage?,
    useSameImage: Boolean,
    onFaceClicked: () -> Unit,
    onBodyClicked: () -> Unit,
    onUseSameImageClicked: () -> Unit,
    showMediaOrCamera: () -> Unit,
    imageLoader: SharedImagesBridge
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
        commonImage = faceImg,
        imageLoader = imageLoader,
        onClick = {
            onFaceClicked()
            showMediaOrCamera()
        }
    )
//    GBImageBoxRequester(
//        modifier = Modifier.fillMaxWidth(),
//        text = stringResource(Res.string.body_image),
//        commonImage = bodyImg,
//        imageLoader = imageLoader,
//        onClick = {
//            onBodyClicked()
//            showMediaOrCamera()
//        }
//    )
    Spacer(Modifier.height(2.dp))
    UseSameImageBox(
        checked = useSameImage,
        enabled = faceImg != null || bodyImg != null,
    ) { onUseSameImageClicked() }
}

@Composable
fun UseSameImageBox(
    checked: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        GBText(
            modifier = Modifier
                .weight(1f)
                .clickable { if (enabled) onClick() }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = stringResource(Res.string.use_same_image),
            style = gBTypography().bodySmall.copy(
                fontStyle = Italic
            )
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { if (enabled) onClick() },
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkmarkColor = gray_box_in_black_bg,
                checkedColor = White,
                uncheckedColor = White
            )
        )
    }
}
