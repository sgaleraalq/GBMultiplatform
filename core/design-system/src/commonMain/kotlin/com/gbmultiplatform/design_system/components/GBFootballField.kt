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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.model.LineUpFormation
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.design_system.style.gBTypography
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.img_football_field

/**
 * This component will define the football field layout.
 * @param [formation] the match formation to be displayed on the field.
 */
@Composable
fun GBFootballField(
    modifier: Modifier = Modifier,
    formation: LineUpFormation
) {
    var boxWidthPx by remember { mutableStateOf(0f) }
    var boxHeightPx by remember { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned { layoutCoordinates ->
                boxWidthPx = layoutCoordinates.size.width.toFloat()
                boxHeightPx = layoutCoordinates.size.height.toFloat()
            }
    ) {
        GBLocalImage(
            modifier = Modifier.fillMaxSize(),
            image = Res.drawable.img_football_field,
            scale = FillWidth
        )

        formation.positions.forEach { position ->
            if (boxWidthPx > 0 && boxHeightPx > 0) {
                val player = UIPlayer(name = "Player Name", image = "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg")
                GBPlayerPosition(
                    player = player,
                    percentX = position.x,
                    percentY = position.y,
                    fieldWidthPx = boxWidthPx,
                    fieldHeightPx = boxHeightPx
                )
            }
        }
    }
}

@Composable
fun GBPlayerPosition(
    player: UIPlayer,
    percentX: Float,
    percentY: Float,
    fieldWidthPx: Float,
    fieldHeightPx: Float
) {
    var columnWidth by remember { mutableStateOf(0) }
    var columnHeight by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .onGloballyPositioned {
                columnWidth = it.size.width
                columnHeight = it.size.height
            }
            .wrapContentSize()
            .graphicsLayer {
                val targetX = fieldWidthPx * percentX
                val targetY = fieldHeightPx * percentY

                translationX = targetX - columnWidth / 2f
                translationY = targetY - columnHeight / 2f
            }
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = spacedBy(4.dp)
        ) {
            GBImage(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(50)),
                image = player.image
            )
            GBText(
                text = player.name, // TODO overlapping
                textColor = White,
                style = gBTypography().bodySmall
            )
        }
    }
}
