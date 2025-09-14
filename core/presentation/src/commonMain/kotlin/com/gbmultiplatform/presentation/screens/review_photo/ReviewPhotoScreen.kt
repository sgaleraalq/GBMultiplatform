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

package com.gbmultiplatform.presentation.screens.review_photo

import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.CommonImage
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.accept
import gbmultiplatform.core.presentation.generated.resources.repeat
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ReviewImageScreen(
    commonImage: CommonImage,
    isFrontCamera: Boolean,
    navigateToCamera: () -> Unit,
    navigateToInsertPlayerScreen: (CommonImage?, CommonImage?) -> Unit,
    viewModel: ReviewPhotoViewModel = koinViewModel<ReviewPhotoViewModel>()
) {
    val image by viewModel.image.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadImage(commonImage, isFrontCamera)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        image?.let { image ->
            ReviewPhotoContent(Modifier.align(Alignment.Center), image)
        }
        Row(
            modifier = Modifier.fillMaxWidth().align(BottomCenter).padding(16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Center
        ) {
            ReviewPhotoTextButton(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.repeat)
            ) { navigateToCamera() }
            ReviewPhotoTextButton(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.accept)
            ) { navigateToInsertPlayerScreen(
                if (commonImage.isFaceImage) commonImage else null,
                if (commonImage.isFaceImage) null else commonImage
            ) }
        }
    }
}

@Composable
fun ReviewPhotoContent(
    modifier: Modifier,
    image: ByteArray
) {
    GBImage(
        modifier = modifier.fillMaxWidth().height(800.dp),
        image = image,
        contentScale = Fit
    )
}

@Composable
fun ReviewPhotoTextButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier.padding(8.dp),
        onClick = { onClick() }
    ) {
        GBText(
            text = text,
            textColor = gray_box_in_black_bg,
            style = gBTypography().bodyMedium
        )
    }
}
