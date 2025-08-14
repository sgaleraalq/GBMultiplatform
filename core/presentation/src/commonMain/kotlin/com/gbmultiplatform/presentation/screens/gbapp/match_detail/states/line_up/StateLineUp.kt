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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.line_up

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBFootballField
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.model.team.TeamModel
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailUIModel
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.starting_eleven
import org.jetbrains.compose.resources.stringResource

internal val benchBgColor = gray_box_in_black_bg
internal val benchHorizontalPadding = 12.dp

@Composable
fun MatchDetailStateLineUp(
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
            StartingElevenHeader(team)
            GBFootballField(
                modifier = Modifier.padding(8.dp),
                formation = matchDetailModel.matchFormation,
                showAnimation = !animationPlayed,
                onAnimationFinished = { animationPlayed = true }
            )
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
        GBText(
            modifier = Modifier.padding(horizontal = 12.dp).weight(1f),
            text = team.name,
            style = gBTypography().bodySmall
        )
        GBText(
            text = stringResource(Res.string.starting_eleven),
            style = gBTypography().bodySmall
        )
    }
}
