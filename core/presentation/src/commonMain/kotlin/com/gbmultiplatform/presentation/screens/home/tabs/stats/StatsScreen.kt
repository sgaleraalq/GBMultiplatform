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

package com.gbmultiplatform.presentation.screens.home.tabs.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.home.tabs.stats.ui.GBSelectedPlayerDialog
import com.gbmultiplatform.presentation.screens.home.tabs.stats.ui.PlayerStatsImagesClassification
import com.gbmultiplatform.presentation.screens.home.tabs.stats.ui.StatSelector
import com.gbmultiplatform.presentation.screens.home.tabs.stats.ui.StatsClassification
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = koinViewModel<StatsViewModel>(),
    state: NavigationState
) {
    val players by viewModel.players.collectAsState()
    val selectedPlayer by viewModel.selectedPlayer.collectAsState()
    val selectedStat by viewModel.selectedStat.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PlayerStatsImagesClassification(
            modifier = Modifier.weight(0.3f),
            players = players,
            navigateWelcome = { state.navigateTo(Welcome) }
        )

        StatSelector(
            selectedStat = selectedStat,
            onStatSelected = { selectedStat ->
                viewModel.changeSelectedStat(selectedStat)
            }
        )

        StatsClassification(
            modifier = Modifier.weight(0.7f),
            players = players,
            onPlayerSelected = { id ->
                viewModel.selectPlayer(id)
            }
        )
    }

    GBSelectedPlayerDialog(
        player = selectedPlayer,
        onDismiss = { viewModel.unselectPlayer() }
    )
}
