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

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gbmultiplatform.design_system.components.GBPlayerCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StatsScreen(
    viewModel: StatsViewModel = koinViewModel<StatsViewModel>()
) {
    val players by viewModel.players.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
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
