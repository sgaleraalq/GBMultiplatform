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

package com.gbmultiplatform.presentation.screens.home

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.gbmultiplatform.design_system.icons.GBIcons
import com.gbmultiplatform.design_system.icons.GBMatchesBottomTab
import com.gbmultiplatform.design_system.icons.GBStatsBottomTab
import com.gbmultiplatform.design_system.icons.GBTeamBottomTab
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.home.tabs.matches.MatchesScreen
import com.gbmultiplatform.presentation.screens.home.tabs.stats.StatsScreen
import com.gbmultiplatform.presentation.screens.home.tabs.team.TeamScreen
import org.jetbrains.compose.resources.painterResource

internal const val MATCHES = "matches"
internal const val STATS = "stats"
internal const val TEAM = "team"

enum class HomeTab(
    val id: String,
    val iconContent: @Composable () -> Unit,
    val content: @Composable (NavigationState) -> Unit
) {
    Matches(
        id = MATCHES,
        iconContent = { Icon(painterResource(GBIcons.GBMatchesBottomTab), null) },
        content = { MatchesScreen(it) }
    ),
    Stats(
        id = STATS,
        iconContent = { Icon(painterResource(GBIcons.GBStatsBottomTab), null) },
        content = { StatsScreen(it) }
    ),
    Team(
        id = TEAM,
        iconContent = { Icon(GBIcons.GBTeamBottomTab, null) },
        content = { TeamScreen(it) }
    )
}
