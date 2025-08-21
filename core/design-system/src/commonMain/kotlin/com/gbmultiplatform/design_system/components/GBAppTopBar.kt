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

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.ic_button_add
import gbmultiplatform.core.design_system.generated.resources.ic_matches

@Composable
fun GBAppTopBar(
    teamLogo: String,
    teamName: String,
    isAdmin: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Bottom,
        horizontalArrangement = spacedBy(24.dp)
    ) {
        /**
         * Team logo
         */
        GBImage(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(50))
                .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
            image = teamLogo
        )

        /**
         * Team name
         */
        GBText(
            modifier = Modifier.weight(1f).padding(bottom = 4.dp),
            text = teamName,
            alignment = Start,
            textColor = White,
            style = MaterialTheme.typography.titleLarge
        )

        /**
         * Inserting button for either matches or players
         */
        if (isAdmin) {
            GBIcon(
                modifier = Modifier.clickable { onClick() },
                icon = Res.drawable.ic_button_add,
                size = 34.dp
            )
        }
    }
}