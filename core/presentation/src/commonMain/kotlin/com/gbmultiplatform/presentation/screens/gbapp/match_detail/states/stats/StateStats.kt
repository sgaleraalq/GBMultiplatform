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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.stats

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.white_in_gray_box
import com.gbmultiplatform.domain.model.player.Stat
import com.gbmultiplatform.domain.model.player.Stat.*
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.RowPlayer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchDetailStateStats(
    matchStats: StatsStateModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        statColumn(GOALS, matchStats.goals)
        statColumn(ASSISTS, matchStats.assists)
        statColumn(PENALTIES_PROVOKED, matchStats.penaltiesProvoked)
        statColumn(CLEAN_SHEETS, matchStats.cleanSheets)
        statColumn(SAVES, matchStats.saves)
        statColumn(YELLOW_CARDS, matchStats.yellowCards)
        statColumn(RED_CARDS, matchStats.redCards)
    }
}

fun LazyListScope.statColumn(
    stat: Stat,
    players: List<UIPlayer>
) {
    if (players.isEmpty()) return
    item {
        StatHeader(stat)
    }
    items(players) {
        RowPlayer(it)
    }
}

@Composable
fun StatHeader(stat: Stat) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(16.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource( stat.icon),
            contentDescription = null,
            tint = Unspecified
        )
        GBText(
            text = stringResource(stat.statName),
            style = gBTypography().titleMedium.copy(fontWeight = Bold),
            textColor = white_in_gray_box
        )
    }
}
