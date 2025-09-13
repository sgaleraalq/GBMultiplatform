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

package com.gbmultiplatform.presentation.screens.team

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBAppTopBar
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.presentation.navigation.Destination.InsertPlayer
import com.gbmultiplatform.presentation.navigation.Destination.PlayerInformation
import com.gbmultiplatform.presentation.navigation.NavigationState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamScreen(
    viewModel: TeamViewModel = koinViewModel<TeamViewModel>(),
    state: NavigationState
) {
    val players by viewModel.players.collectAsState()

    Column {
        GBAppTopBar(isAdmin = true) {
            state.navigateTo(InsertPlayer(null, null))
        }
        TeamPlayerList(players) {
            state.navigateTo(PlayerInformation)
        }
    }
}

@Composable
fun TeamPlayerList(
    players: List<PlayerInformationModel>,
    onPlayerClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3),
        horizontalArrangement = spacedBy(8.dp),
        verticalArrangement = spacedBy(8.dp),
        contentPadding = PaddingValues(12.dp)
    ) {
        item(span = { GridItemSpan(3) }) {

        }
        items(players) { player ->
            PlayerCard(player) {
                onPlayerClicked(player.id)
            }
        }
    }
}

@Composable
fun PlayerCard(player: PlayerInformationModel, onPlayerClicked: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Transparent
        ),
        onClick = { onPlayerClicked() }
    ) {
        Box {
            GBImage(
                modifier = Modifier.fillMaxSize(),
                image = player.faceImage
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .align(TopStart)
                    .size(24.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(50)
                    )
                    .border(
                        width = 1.dp,
                        color = Black,
                        shape = CircleShape
                    )
                    .padding(4.dp),
                contentAlignment = Center
            ) {
                GBText(
                    text = player.dorsal.toString(),
                    textColor = gray_box_in_black_bg,
                    style = gBTypography().bodySmall
                )
            }
        }
    }
}
