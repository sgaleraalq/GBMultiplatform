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

package com.gbmultiplatform.presentation.screens.gbapp.stats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gb_dialog_background
import com.gbmultiplatform.design_system.style.player_card_name_text_color
import com.gbmultiplatform.design_system.style.player_card_stat_text_color
import com.gbmultiplatform.model.player.Stats
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StatSelector(
    selectedStat: Stats,
    onStatSelected: (Stats) -> Unit
) {
    var showChangeSelectedStat by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clickable{
                showChangeSelectedStat = true
            }
            .padding(vertical = 8.dp, horizontal = 48.dp),
        verticalAlignment = CenterVertically
    ) {
        GBText(
            modifier = Modifier.weight(1f),
            text = stringResource(selectedStat.statName).uppercase(),
            alignment = Start,
            textColor = player_card_name_text_color,
            style = MaterialTheme.typography.bodyMedium,
        )

        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(selectedStat.icon),
            contentDescription = null,
            tint = Unspecified
        )
    }

    HorizontalDivider(
        Modifier.fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 24.dp)
            .background(White)
    )

    if (showChangeSelectedStat) {
        GBShowChangeSelectedStatDialog(
            onDismiss = { showChangeSelectedStat = false },
            onStatSelected = { onStatSelected(it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GBShowChangeSelectedStatDialog(
    onDismiss: () -> Unit,
    onStatSelected: (Stats) -> Unit
) {
    val scrollState = rememberScrollState()

    BasicAlertDialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
                .background(
                    color = gb_dialog_background,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            Stats.entries.forEach {
                SelectStatDialogComponent(
                    statIcon = it.icon,
                    statName = it.statName,
                    onStatSelected = {
                        onStatSelected(it)
                        onDismiss()
                    },
                    isLastItem = it == Stats.GAMES_PLAYED
                )
            }
        }
    }
}

@Composable
fun SelectStatDialogComponent(
    statIcon: DrawableResource,
    statName: StringResource,
    onStatSelected: () -> Unit,
    isLastItem: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStatSelected()
            }
            .padding(12.dp),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(statIcon),
            contentDescription = null,
            tint = Unspecified
        )
        GBText(
            modifier = Modifier
                .padding(start = 12.dp),
            text = stringResource(statName),
            textColor = player_card_name_text_color,
            style = MaterialTheme.typography.bodyMedium,
            alignment = Start
        )
    }
    if (!isLastItem) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.25.dp)
                .padding(horizontal = 12.dp)
                .background(player_card_stat_text_color)
        )
    }
}
