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

package com.gbmultiplatform.presentation.screens.match_detail.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBBackButton
import com.gbmultiplatform.design_system.components.GBTeam
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography


@Composable
fun MatchDetailHeader(
    localTeam: TeamDetailModel,
    visitorTeam: TeamDetailModel,
    onBackPressed: () -> Unit
) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
        GBBackButton { onBackPressed() }
        GBMatchDetailResult(Modifier.weight(1f), localTeam, visitorTeam)
        GBBackButton(isVisible = false) { }
    }
}

@Composable
fun GBMatchDetailResult(modifier: Modifier, localTeam: TeamDetailModel, visitorTeam: TeamDetailModel) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ) {
        GBTeamDetailResult(Modifier.weight(1f), localTeam)
        GBText(":", Modifier.padding(horizontal = 8.dp))
        GBTeamDetailResult(Modifier.weight(1f), visitorTeam)
    }

}

@Composable
fun GBTeamDetailResult(
    modifier: Modifier,
    teamModel: TeamDetailModel
) {
    Row(modifier, verticalAlignment = CenterVertically) {
        if (!teamModel.isLocal) {
            GBText(teamModel.score.toString(), style = gBTypography().headlineMedium)
        }
        Spacer(Modifier.weight(1f))
        GBTeam(36.dp, teamModel.team.logo)
        Spacer(Modifier.weight(1f))
        if (teamModel.isLocal) {
            GBText(teamModel.score.toString(), style = gBTypography().headlineMedium)
        }
    }
}
