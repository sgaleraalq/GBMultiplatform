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

package com.gbmultiplatform.presentation.screens.player_detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.GazteluBiraUtils.GAZTELU_BIRA_LOGO
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.primaryRed
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.presentation.screens.player_detail.LOGO_SIZE
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.view_stats
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PlayerDetailInformationBox(
    modifier: Modifier,
    playerName: String?,
    playerStats: PlayerStatsModel?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(1.25f / 3f)
            .padding(top = 25.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(36.dp, 36.dp, 0.dp, 0.dp)
            )
    ) {
        TeamImage(
            modifier = Modifier.align(TopCenter),
            logo = GAZTELU_BIRA_LOGO,
            logoSize = LOGO_SIZE
        )
        PlayerInformation(playerName, playerStats)
    }
}

@Composable
internal fun PlayerInformation(
    playerName: String?,
    playerStats: PlayerStatsModel?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (LOGO_SIZE / 2 + 16).dp)
    ) {
        PlayerName(playerName)
        PlayerBasicStats(Modifier.weight(1f), playerStats)
        ViewStatsButton()
    }
}

@Composable
internal fun PlayerName(playerName: String?) {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = playerName?.uppercase() ?: "",
        alignment = Center,
        textColor = primaryRed,
        style = gBTypography().titleLarge.copy(
            fontWeight = Bold
        )
    )
}

@Composable
internal fun PlayerBasicStats(
    modifier: Modifier,
    playerStats: PlayerStatsModel?
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        PlayerStatItem(Modifier.weight(1f))
        PersonalizedSpacer()
        PlayerStatItem(Modifier.weight(1f))
        PersonalizedSpacer()
        PlayerStatItem(Modifier.weight(1f))
    }
}

@Composable
internal fun PlayerStatItem(
    modifier: Modifier,
    statValue: String = "10",
    playerStat: String = "Goals"
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(Modifier.weight(1f))
        HorizontalDivider(thickness = 1.dp)
        GBText(
            modifier = Modifier.weight(1f).padding(vertical = 6.dp),
            text = statValue,
            alignment = Center,
            style = gBTypography().headlineLarge.copy(
                fontWeight = Bold
            ),
            textColor = Black
        )
        GBText(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            text = playerStat,
            style = gBTypography().bodyMedium,
            alignment = Center,
            textColor = Black
        )
        HorizontalDivider(thickness = 1.dp)
        Spacer(Modifier.weight(1f))
    }
}

// TODO
@Composable
fun PersonalizedSpacer() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.5f)
            .padding(vertical = 8.dp)
            .background(color = primaryRed, shape = RoundedCornerShape(50))
    )
}

@Composable
internal fun ViewStatsButton() {
    GBElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        text = stringResource(Res.string.view_stats),
        backgroundColor = primaryRed,
        textColor = White,
        roundness = 32,
        onClick = { }
    )
}
