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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.GazteluBiraUtils.GAZTELU_BIRA_LOGO
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.primaryBlue
import com.gbmultiplatform.design_system.style.primaryRed
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.domain.model.player.Stat.ASSISTS
import com.gbmultiplatform.domain.model.player.Stat.GAMES_PLAYED
import com.gbmultiplatform.domain.model.player.Stat.GOALS
import com.gbmultiplatform.presentation.screens.player_detail.LOGO_SIZE
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_football_ball
import gbmultiplatform.core.presentation.generated.resources.view_stats
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun PlayerDetailInformationBox(
    modifier: Modifier,
    player: PlayerInformationModel?,
    playerStats: PlayerStatsModel?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(1.25f / 3f)
            .background(
                color = White,
                shape = RoundedCornerShape(36.dp, 36.dp, 0.dp, 0.dp)
            )
    ) {
        Image(
            painter = painterResource(Res.drawable.img_football_ball),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.025f),
            contentScale = Crop
        )
        TeamImage(
            modifier = Modifier.align(TopCenter),
            logo = GAZTELU_BIRA_LOGO,
            logoSize = LOGO_SIZE
        )
        PlayerInformation(player, playerStats)
    }
}

@Composable
internal fun PlayerInformation(
    player: PlayerInformationModel?,
    playerStats: PlayerStatsModel?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = (LOGO_SIZE / 2 + 16).dp, bottom = 16.dp)
    ) {
        Spacer(Modifier.weight(1f))
        PlayerName(player?.name)
        Spacer(Modifier.height(24.dp))
//        PlayerPositionAndDorsal(player)
        PlayerBasicStats(playerStats)
        Spacer(Modifier.height(12.dp))
        ViewStatsButton { /* TODO */ }
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

//@Composable
//internal fun PlayerPositionAndDorsal(
//    player: PlayerInformationModel?
//) {
//    Row(
//        Modifier.fillMaxWidth().padding(12.dp),
//        verticalAlignment = CenterVertically,
//        horizontalArrangement = Arrangement.Center
//    ) {
//        GBText(
//            text = player?.dorsal.toString(),
//            style = gBTypography().titleMedium,
//            textColor = Black
//        )
//        Spacer(Modifier.width(12.dp))
//        GBText(
//            text = stringResource(player?.position?.positionName ?: UNDEFINED.positionName).uppercase(),
//            style = gBTypography().titleMedium,
//            textColor = Black
//        )
//    }
//}

@Composable
internal fun PlayerBasicStats(
    playerStats: PlayerStatsModel?
) {
    var itemHeight by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        PlayerStatItem(
            modifier = Modifier.weight(1f).onGloballyPositioned { coordinates ->
                itemHeight = coordinates.size.height
            },
            statValue = playerStats?.gamesPlayed.toString(),
            playerStat = stringResource(GAMES_PLAYED.statName)
        )
        PersonalizedSpacer(itemHeight)
        PlayerStatItem(
            modifier = Modifier.weight(1f),
            statValue = playerStats?.goals.toString(),
            playerStat = stringResource(GOALS.statName)
        )
        PersonalizedSpacer(itemHeight)
        PlayerStatItem(
            modifier = Modifier.weight(1f),
            statValue = playerStats?.assists.toString(),
            playerStat = stringResource(ASSISTS.statName)
        )
    }
}

@Composable
internal fun PlayerStatItem(
    modifier: Modifier,
    statValue: String,
    playerStat: String
) {
    Column(
        modifier = modifier.padding(12.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(thickness = 1.dp, color = primaryRed)
        Spacer(Modifier.height(12.dp))
        GBText(
            text = statValue,
            alignment = Center,
            style = gBTypography().headlineLarge.copy(
                fontWeight = Bold
            ),
            textColor = primaryBlue
        )
        Spacer(Modifier.height(4.dp))
        GBText(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            text = playerStat,
            style = gBTypography().bodySmall,
            alignment = Center,
            textColor = Black
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(thickness = 1.dp, color = primaryRed)
    }
}

@Composable
fun PersonalizedSpacer(itemHeight: Int) {
    Column(
        modifier = Modifier
            .width(8.dp)
            .height(with(LocalDensity.current) { itemHeight.toDp() })
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        repeat(8) {
            DiagonalLine()
        }
    }
}

@Composable
fun DiagonalLine() {
    Canvas(Modifier.fillMaxWidth().height(5.dp)) {
        val start = Offset(0f, 0f)
        val end = Offset(size.width * 1f, size.height * 1f)

        drawLine(
            color = primaryBlue,
            start = start,
            end = end,
            strokeWidth = 4f
        )
    }
}

@Composable
internal fun ViewStatsButton(
    viewStats: () -> Unit
) {
    GBElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.view_stats),
        backgroundColor = primaryRed,
        textColor = White,
        roundness = 32,
        onClick = { viewStats() }
    )
}
