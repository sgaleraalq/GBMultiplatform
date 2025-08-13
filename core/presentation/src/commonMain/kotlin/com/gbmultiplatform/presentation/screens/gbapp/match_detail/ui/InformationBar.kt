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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Thin
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.player_card_name_text_color
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.DETAILS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LINEUPS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LOADING
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.STATS
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
    Column(Modifier.padding(bottom = 8.dp).padding(horizontal = 12.dp)) {
        Row(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
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
            textColor = if (isHighlighted) {
                White
            } else {
                player_card_name_text_color
            },
            style = gBTypography().bodyMedium.copy(
                fontWeight = if (isHighlighted) Bold else Thin
            )
        )
    }
}

@Composable
internal fun MatchDetailInformationPointer(state: MatchDetailState) {
    val density = LocalDensity.current
    var totalWidth by remember { mutableStateOf(0.dp) }

    val targetIndex = when (state) {
        DETAILS -> 0
        LINEUPS -> 1
        STATS -> 2
        LOADING -> return
    }

    val cellWidth = if (totalWidth > 0.dp) totalWidth / 3 else 0.dp
    val pointerPadding = 32.dp
    val offsetX by animateDpAsState(
        targetValue = (cellWidth * targetIndex)+ pointerPadding/2,
        animationSpec = tween(400, easing = FastOutSlowInEasing),
        label = "pointerOffset"
    )

    Box(
        Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                totalWidth = with(density) { it.size.width.toDp() }
            }
            .height(2.dp)
    ) {
        Box(
            Modifier
                .offset(x = offsetX)
                .width(cellWidth - pointerPadding)
                .fillMaxHeight()
                .background(White)
        )
    }
}
