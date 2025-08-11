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

package com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Thin
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.player_card_stat_text_color
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.DETAILS
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.LINEUPS
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.STATS
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.details
import gbmultiplatform.core.presentation.generated.resources.line_ups
import gbmultiplatform.core.presentation.generated.resources.stats
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchDetailInformationBar(
    state: MatchDetailState,
    onDetailsClicked: () -> Unit,
    onLineUpsClicked: () -> Unit,
    onStatsClicked: () -> Unit
) {
    Column {
        Row(Modifier.fillMaxWidth().padding(12.dp)) {
            MatchDetailInformationBarItem(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.details),
                isHighlighted = state == DETAILS,
                onClick = { onDetailsClicked() }
            )
            MatchDetailInformationBarItem(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.line_ups),
                isHighlighted = state == LINEUPS,
                onClick = { onLineUpsClicked() }
            )
            MatchDetailInformationBarItem(
                modifier = Modifier.weight(1f),
                text = stringResource(Res.string.stats),
                isHighlighted = state == STATS,
                onClick = { onStatsClicked() }
            )
        }
        MatchDetailInformationPointer(state)
    }
}

@Composable
fun MatchDetailInformationBarItem(
    modifier: Modifier,
    text: String,
    isHighlighted: Boolean,
    onClick: () -> Unit
) {
    Box(modifier.clickable { onClick() }, contentAlignment = Center) {
        GBText(
            text = text,
            style = gBTypography().bodyMedium.copy(
                fontWeight = if (isHighlighted) Bold else Thin
            )
        )
    }
}

@Composable
fun MatchDetailInformationPointer(state: MatchDetailState) {
    LaunchedEffect(state) {

    }

    Box(Modifier.fillMaxWidth()) {
        Box(Modifier.width(50.dp).height(2.dp).background(player_card_stat_text_color))
    }
}
