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

package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.description_insert_player_image
import gbmultiplatform.core.design_system.generated.resources.img_example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GBPlayerImage(
    modifier: Modifier = Modifier,
    image: String?
) {
    GBImage(
        modifier = modifier
            .size(24.dp)
            .clip(RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(50)
            ),
        image = image
    )
}

@Composable
fun GBPlayerImage(
    modifier: Modifier = Modifier,
    image: DrawableResource
) {
    GBImage(
        modifier = modifier
            .size(24.dp)
            .clip(RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(50)
            ),
        image = image
    )
}

@Composable
fun GBImage(
    modifier: Modifier = Modifier,
    image: String?,
    contentScale: ContentScale = Crop,
    saverImage: DrawableResource = Res.drawable.img_example // TODO
) {
    var isLoading by remember { mutableStateOf(false) }
    Box(modifier) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = image,
            contentScale = contentScale,
            contentDescription = null,
            onLoading = { isLoading = true },
            onError = { isLoading = false },
            onSuccess = { isLoading = false },
            error = painterResource(saverImage),
            fallback = painterResource(saverImage)
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center)
            )
        }
    }
}

@Composable
fun GBImage(
    modifier: Modifier = Modifier,
    image: DrawableResource
) {
    Image(
        modifier = modifier,
        painter = painterResource(image),
        contentScale = Crop,
        contentDescription = null,
    )
}

@Composable
fun GBImage(
    modifier: Modifier = Modifier,
    image: ByteArray,
    contentScale: ContentScale = Crop
) {
    AsyncImage(
        modifier = modifier,
        model = image,
        contentScale = contentScale,
        contentDescription = stringResource(Res.string.description_insert_player_image)
    )
}

@Composable
fun GBLocalImage(
    modifier: Modifier,
    image: DrawableResource,
    scale: ContentScale = Crop
) {
    Image(
        modifier = modifier,
        painter = painterResource(image),
        contentScale = scale,
        contentDescription = null,
    )
}
