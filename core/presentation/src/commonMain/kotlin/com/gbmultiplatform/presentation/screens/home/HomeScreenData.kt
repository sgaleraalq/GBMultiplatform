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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBBottomNavItem
import com.gbmultiplatform.design_system.icons.GBIcons
import com.gbmultiplatform.design_system.icons.GBMatchesBottomTab
import com.gbmultiplatform.design_system.icons.GBStatsBottomTab
import com.gbmultiplatform.design_system.icons.GBTeamBottomTab
import com.gbmultiplatform.presentation.navigation.NavigationState
import org.jetbrains.compose.resources.painterResource

internal const val MATCHES = "matches"
internal const val STATS = "stats"
internal const val TEAM = "team"
enum class HomeBottomTab(
    val id: String,
    val iconContent: @Composable () -> Unit,
    val content: @Composable (NavigationState) -> Unit
) {
    Matches(
        id = "matches",
        iconContent = { Icon(painterResource(GBIcons.GBMatchesBottomTab), null) },
        content = {  }
    ),
    Stats(
        id = "stats",
        iconContent = { Icon(painterResource(GBIcons.GBStatsBottomTab), null) },
        content = {}
    ),
    Team(
        id = "team",
        iconContent = { Icon(GBIcons.GBTeamBottomTab, null) },
        content = {}
    )
}

@Composable
fun GBBottomNavigation(
    states: List<HomeBottomTab>
) {
    Column(Modifier.shadow(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = CenterVertically
        ) {
            states.forEachIndexed { index, bottomNavTab ->
                GBBottomNavItem(
                    isSelected = true, // TODO
                    content = bottomNavTab.iconContent,
                    navigate = { },
                    isMiddleScreen = index == states.size / 2
                )
            }
        }
    }
}
