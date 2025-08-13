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

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBFootballField
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.design_system.style.white_in_gray_box
import com.gbmultiplatform.model.team.TeamModel
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailUIModel
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.bench
import gbmultiplatform.core.presentation.generated.resources.managers
import gbmultiplatform.core.presentation.generated.resources.starting_eleven
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal val benchBgColor = gray_box_in_black_bg
internal val benchHorizontalPadding = 12.dp

@Composable
fun MatchDetailLineUpsScreen(
    team: TeamModel,
    modifier: Modifier,
    matchDetailModel: MatchDetailUIModel,
) {
    var animationPlayed by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            GBFootballField(
                modifier = Modifier.padding(8.dp),
                formation = matchDetailModel.matchFormation,
                showAnimation = !animationPlayed,
                onAnimationFinished = { animationPlayed = true }
            )
            StartingElevenHeader(team)
            Spacer(Modifier.height(8.dp))
        }
        managers(matchDetailModel.managers)
        benchPlayers(matchDetailModel.benchPlayers)
    }
}

@Composable
fun StartingElevenHeader(team: TeamModel) {
    Row(
        Modifier.padding(horizontal = 32.dp),
        verticalAlignment = CenterVertically
    ) {
        GBImage(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(50))
                .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
            image = team.logo
        )
        Spacer(Modifier.weight(1f))
        GBText(
            text = stringResource(Res.string.starting_eleven),
            style = gBTypography().bodySmall
        )
    }
}

fun LazyListScope.managers(managers: List<UIPlayer>) {
    item {
        ManagerHeaderText()
        BenchHorizontalDivider()
        BenchListSpacer()
    }
    items(managers) { manager ->
        BenchPlayer(manager)
    }
}

fun LazyListScope.benchPlayers(benchPlayers: List<UIPlayer>) {
    item {
        BenchListSpacer(12)
        BenchHeaderText()
        BenchHorizontalDivider()
        BenchListSpacer()
    }
    items(benchPlayers) { player ->
        BenchPlayer(player)
    }
}

@Composable
fun BenchListSpacer(height: Int = 8) {
    Spacer(
        Modifier.height(height.dp).fillMaxWidth().padding(horizontal = 12.dp)
            .background(benchBgColor)
    )
}

@Composable
fun BenchHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = benchHorizontalPadding),
        thickness = 0.25.dp,
        color = White
    )
}

@Composable
fun ManagerHeaderText() {
    HeaderText(Res.string.managers)
}

@Composable
fun BenchHeaderText() {
    HeaderText(Res.string.bench, false)
}

@Composable
fun HeaderText(text: StringResource, isManager: Boolean = true) {
    GBText(
        modifier = Modifier
            .padding(horizontal = benchHorizontalPadding)
            .background(
                color = benchBgColor,
                shape = RoundedCornerShape(
                    topStart = if (isManager) 12.dp else 0.dp,
                    topEnd = if (isManager) 12.dp else 0.dp
                )
            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        text = stringResource(text).uppercase(),
        style = gBTypography().bodyLarge.copy(
            fontWeight = Bold,
            color = White
        )
    )
}

@Composable
fun BenchPlayer(player: UIPlayer) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = benchHorizontalPadding)
            .background(benchBgColor)
            .padding(8.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(12.dp)
    ) {
        GBImage(
            modifier = Modifier.size(34.dp).clip(RoundedCornerShape(50)),
            image = player.image,
        )
        GBText(
            text = player.name,
            textColor = white_in_gray_box,
            style = gBTypography().bodyMedium
        )
    }
}
