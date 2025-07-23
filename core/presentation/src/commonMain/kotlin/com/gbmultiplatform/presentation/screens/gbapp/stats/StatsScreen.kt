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

package com.gbmultiplatform.presentation.screens.gbapp.stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBPlayerCard
import com.gbmultiplatform.model.player.Player
import com.gbmultiplatform.model.player.Stats
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.ic_penalties
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = koinViewModel<StatsViewModel>()
) {
    val players by viewModel.players.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StatsImagesClassification(Modifier.weight(0.3f))
        StatsClassification(Modifier.weight(0.7f),players)
    }
}

@Composable
fun StatsImagesClassification(
    modifier: Modifier
) {
    val allIcons = Stats.entries
    Box(
        modifier = modifier.fillMaxWidth().padding(32.dp)
    ) {
        Row {
            allIcons.forEach {
                Spacer(Modifier.width(4.dp))
                Icon(
                    painter = painterResource(it.icon),
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,
                    tint = Unspecified
                )
                Spacer(Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun StatsClassification(
    modifier: Modifier,
    players: List<Player>
){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 12.dp)
    ) {
        items(players) { player ->
            GBPlayerCard(
                image = player.image,
                name = player.name,
                stat = player.goals.toString(),
                onClick = {  }
            )
        }
    }
}
