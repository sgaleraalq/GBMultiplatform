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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.ic_camera
import gbmultiplatform.core.design_system.generated.resources.ic_media

@Composable
fun GBMediaOrCamera(
    title: String,
    dismiss: () -> Unit,
    onMediaClicked: () -> Unit,
    onCameraClicked: () -> Unit
) {
    GBDialog(true, dismiss = dismiss) {
        Column(
            modifier = Modifier
                .background(
                    color = gray_box_in_black_bg,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            GBText(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = gBTypography().bodySmall,
                alignment = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gray_box_in_black_bg),
                verticalAlignment = CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onMediaClicked() }
                        .padding(12.dp),
                    contentAlignment = Center
                ) {
                    GBIcon(icon = Res.drawable.ic_media)
                }
                VerticalDivider(Modifier.height(24.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onCameraClicked() }
                        .padding(12.dp),
                    contentAlignment = Center
                ) {
                    GBIcon(icon = Res.drawable.ic_camera)
                }
            }
        }
    }
}
