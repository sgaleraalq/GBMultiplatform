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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import coil3.compose.AsyncImage
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.img_example
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GBImage(
    modifier: Modifier = Modifier,
    image: String,
    saverImage: DrawableResource = Res.drawable.img_example // TODO
) {
    AsyncImage(
        modifier = modifier,
        model = image,
        contentScale = Crop,
        contentDescription = null,
        error = painterResource(saverImage),
        fallback = painterResource(saverImage)
    )
}

@Composable
fun GBImage(
    modifier: Modifier = Modifier,
    image: ByteArray?,
    saverImage: DrawableResource = Res.drawable.img_example // TODO
) {
    AsyncImage(
        modifier = modifier,
        model = image,
        contentScale = Crop,
        contentDescription = null,
        error = painterResource(saverImage),
        fallback = painterResource(saverImage)
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
