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

package com.gbmultiplatform.presentation.screens.gbapp.team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.domain.model.player.PlayerModel
import com.gbmultiplatform.presentation.screens.gbapp.matches.ui.MatchesScreenHeader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamScreen(
    viewModel: TeamViewModel = koinViewModel<TeamViewModel>()
) {
    val players by viewModel.players.collectAsState()

    LazyColumn(Modifier.fillMaxSize()) {
        item {
            MatchesScreenHeader(
                appTeam = viewModel.appTeam
            )
        }
        items(players.chunked(3)) { rowPlayers ->
            rowPlayers.forEach { player ->
                PlayerCard(player)
            }
        }
    }
}

@Composable
fun PlayerCard(player: PlayerModel) {
    Card {
        Box {
            GBText(
                modifier = Modifier.align(TopStart),
                text = "1"
            )
            GBImage(Modifier.size(24.dp), player.image)
        }
    }
}
