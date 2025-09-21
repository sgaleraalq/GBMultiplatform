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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.player_card_name_text_color
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource

@Composable
fun GBBackButton(
    modifier: Modifier = Modifier,
    color: Color = player_card_name_text_color,
    isVisible: Boolean = true,
    onClick: () -> Unit
) {
    var isRunning by remember { mutableStateOf(false) }

    Icon(
        modifier = modifier
            .alpha(if (isVisible) 1f else 0f)
            .clickable {
                if (!isRunning && isVisible) {
                    onClick()
                    isRunning = true
                }
            }
            .padding(12.dp)
            .size(24.dp),
        painter = painterResource(Res.drawable.ic_arrow_back),
        contentDescription = null,
        tint = color
    )
}
