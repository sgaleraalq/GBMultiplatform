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

package com.gbmultiplatform.presentation.screens.home.tabs.stats.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gb_dialog_background
import com.gbmultiplatform.design_system.style.player_card_stat_text_color
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.domain.model.player.Stat
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GBSelectedPlayerDialog(
    player: PlayerStatsModel?,
    onDismiss: () -> Unit
) {
    if (player == null) return

    val scrollState = rememberScrollState()

    BasicAlertDialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = gb_dialog_background,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            GBPlayerDialogHeader(
                image = player.image,
                name = player.name
            )

            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                listOf(
                    Stat.PERCENTAGE to "${player.percentage} %",
                    Stat.GOALS to player.goals.toString(),
                    Stat.ASSISTS to player.assists.toString(),
                    Stat.PENALTIES_PROVOKED to player.penaltiesProvoked.toString(),
                    Stat.CLEAN_SHEETS to player.cleanSheets.toString(),
                    Stat.SAVES to player.saves.toString(),
                    Stat.YELLOW_CARDS to player.yellowCards.toString(),
                    Stat.RED_CARDS to player.redCards.toString(),
                    Stat.GAMES_PLAYED to player.gamesPlayed.toString()
                ).forEach { (stat, value) ->
                    GBPlayerDialogStat(
                        statName = stat.statName,
                        statIcon = stat.icon,
                        stat = value
                    )
                }
            }
        }
    }
}

@Composable
fun GBPlayerDialogHeader(
    image: String,
    name: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ){
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            contentAlignment = Center
        ) {
            GBImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(50))
                    .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
                image = image
            )
        }
        GBText(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 8.dp, end = 8.dp),
            text = name,
            textColor = White,
            alignment = TextAlign.Center
        )
    }
}


@Composable
fun GBPlayerDialogStat(
    statName: StringResource,
    statIcon: DrawableResource,
    stat: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(statIcon),
            contentDescription = null,
            tint = Unspecified
        )
        Spacer(Modifier.width(12.dp))
        GBText(
            modifier = Modifier.weight(1f),
            text = stringResource(statName),
            alignment = Start,
            textColor = player_card_stat_text_color,
            style = MaterialTheme.typography.bodySmall
        )
        GBText(
            text = stat,
            alignment = Start,
            textColor = player_card_stat_text_color,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
