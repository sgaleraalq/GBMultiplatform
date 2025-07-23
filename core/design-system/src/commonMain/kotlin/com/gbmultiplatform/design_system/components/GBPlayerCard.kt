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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.player_card_background_bottom_gradient
import com.gbmultiplatform.design_system.style.player_card_background_top_gradient
import com.gbmultiplatform.design_system.style.player_card_name_text_color
import com.gbmultiplatform.design_system.style.player_card_stat_text_color
import kotlin.Float.Companion.POSITIVE_INFINITY

@Composable
fun GBPlayerCard(
    image: String,
    name: String,
    stat: String,
    onClick: () -> Unit = { }
) {
    val cardBackgroundColor = Brush.linearGradient(
        colors = listOf(
            player_card_background_top_gradient,
            player_card_background_bottom_gradient
        ),
        start = Offset(0f, POSITIVE_INFINITY),
        end = Offset(0f, 0f)
    )

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            modifier = Modifier
                .background(brush = cardBackgroundColor)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = CenterVertically,
        ) {
            GBImage(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(50))
                    .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
                image = image
            )
            GBText(
                modifier = Modifier.weight(1f),
                text = name,
                textColor = player_card_name_text_color,
                alignment = Start
            )
            GBText(
                text = stat,
                textColor = player_card_stat_text_color,
                alignment = Center
            )
        }
    }
}
