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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBDialog
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.model.player.Position
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.select_dorsal
import gbmultiplatform.core.presentation.generated.resources.select_position
import org.jetbrains.compose.resources.stringResource

@Composable
fun DorsalDialog(
    show: Boolean,
    dorsals: List<Int>,
    onDorsalClicked: (Int) -> Unit,
    dismiss: () -> Unit
) {
    GBDialog(
        show = show,
        dismiss = { dismiss() }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .background(
                    color = gray_box_in_black_bg,
                    shape = RoundedCornerShape(12.dp)
                ),
            columns = GridCells.Fixed(3),
            horizontalArrangement = spacedBy(8.dp),
            verticalArrangement = spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            item(span = { GridItemSpan(3) }) {
                GBText(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    text = stringResource(Res.string.select_dorsal),
                    style = gBTypography().bodyLarge,
                    alignment = Center
                )
            }
            items(dorsals) { dorsal ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onDorsalClicked(dorsal)
                            dismiss()
                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Black),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Transparent)
                            .fillMaxWidth(),
                    ) {
                        GBText(
                            text = dorsal.toString(),
                            modifier = Modifier.fillMaxWidth(),
                            alignment = Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PositionDialog(
    show: Boolean,
    onPositionClicked: (Position) -> Unit,
    dismiss: () -> Unit
) {
    val positions = Position.entries
    GBDialog(
        show = show,
        dismiss = { dismiss() }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    shape = RoundedCornerShape(12.dp),
                    color = gray_box_in_black_bg
                )
        ) {
            GBText(
                text = stringResource(Res.string.select_position),
                style = gBTypography().bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                alignment = Center
            )
            HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 8.dp))
            positions.forEach { position ->
                GBText(
                    text = stringResource(position.positionName),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            onPositionClicked(position)
                            dismiss()
                        },
                    alignment = Center
                )
            }
        }
    }
}
