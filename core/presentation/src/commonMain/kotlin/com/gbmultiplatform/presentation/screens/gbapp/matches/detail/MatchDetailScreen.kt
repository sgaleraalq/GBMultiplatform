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

package com.gbmultiplatform.presentation.screens.gbapp.matches.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.DETAILS
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.LINEUPS
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.LOADING
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.MatchDetailViewModel.MatchDetailState.STATS
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui.MatchDetailDetailsScreen
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui.MatchDetailHeader
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui.MatchDetailInformationBar
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui.MatchDetailLineUpsScreen
import com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui.MatchDetailStatsScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MatchDetailScreen(
    state: NavigationState,
    viewModel: MatchDetailViewModel = koinViewModel<MatchDetailViewModel>()
) {
    val localTeam = viewModel.localTeam
    val visitorTeam = viewModel.visitorTeam
    val detailState by viewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        MatchDetailHeader(localTeam, visitorTeam) { state.navigateBack() }
        MatchDetailInformationBar(
            state = detailState,
            onDetailsClicked = { viewModel.changeUiState(DETAILS) },
            onLineUpsClicked = { viewModel.changeUiState(LINEUPS) },
            onStatsClicked = { viewModel.changeUiState(STATS) }
        )
        when (detailState) {
            LOADING -> { /* Loading */
            }

            DETAILS -> {
                MatchDetailDetailsScreen()
            }

            LINEUPS -> {
                MatchDetailLineUpsScreen(
                    modifier = Modifier.weight(1f)
                )
            }

            STATS -> {
                MatchDetailStatsScreen()
            }
        }
    }
}
