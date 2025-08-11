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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale.Companion.FillWidth
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.model.LineUpFormation
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.img_football_field

/**
 * This component will define the football field layout.
 * @param [formation] the match formation to be displayed on the field.
 */
@Composable
fun GBFootballField(
    formation: LineUpFormation
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val fieldWidth = maxWidth
        val fieldHeight = maxHeight

        GBLocalImage(
            modifier = Modifier.fillMaxSize(),
            image = Res.drawable.img_football_field,
            scale = FillWidth
        )

        formation.positions.forEach { position ->
            GBPlayerPosition(
                modifier = Modifier.absoluteOffset(
                    x = (position.x * fieldWidth.value).dp - 12.dp,
                    y = (position.y * fieldHeight.value).dp - 12.dp
                )
            )
        }
    }
}

@Composable
fun GBPlayerPosition(
    modifier: Modifier
) {
    Box(
        modifier
            .size(24.dp)
            .background(color = White, shape = RoundedCornerShape(50))
    )
}
