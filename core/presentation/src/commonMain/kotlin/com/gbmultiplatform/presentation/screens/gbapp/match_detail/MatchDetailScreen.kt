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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gbmultiplatform.helper.GazteluBiraUtils.GAZTELU_BIRA
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.DETAILS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LINEUPS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LOADING
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.STATS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailHeader
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailInformationBar
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailStateDetails
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailStateLineUp
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailStateStats
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.MatchDetailUIModelFactory
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.StateLoading
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
            LOADING -> {
                StateLoading()
            }

            DETAILS -> {
                MatchDetailStateDetails(
                    title = "Title",
                    description = "Description"
                )
            }

            LINEUPS -> {
                MatchDetailStateLineUp(
                    team = GAZTELU_BIRA,
                    modifier = Modifier.weight(1f),
                    matchDetailModel = MatchDetailUIModelFactory.create()
                )
            }

            STATS -> {
                MatchDetailStateStats()
            }
        }
    }
}


//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun MatchDetailScreen(
//    state: NavigationState,
//    viewModel: MatchDetailViewModel = koinViewModel<MatchDetailViewModel>()
//) {
//
//    val localTeam = viewModel.localTeam
//    val visitorTeam = viewModel.visitorTeam
//    val detailState by viewModel.state.collectAsState()
//
//    var previousState by remember { mutableStateOf(detailState) }
//    val order = mapOf(DETAILS to 0, LINEUPS to 1, STATS to 2)
//
//    val direction = remember(detailState) {
//        val prevIndex = order[previousState] ?: 0
//        val newIndex = order[detailState] ?: 0
//        previousState = detailState
//        if (newIndex > prevIndex) 1 else -1
//    }
//    val statesList = listOf(DETAILS, LINEUPS, STATS)
//
//    fun onSwipe(deltaX: Float) {
//        val currentIndex = order[detailState] ?: 0
//        if (deltaX > 0) {
//            if (currentIndex > 0) {
//                viewModel.changeUiState(statesList[currentIndex - 1])
//            }
//        } else if (deltaX < 0) {
//            if (currentIndex < statesList.lastIndex) {
//                viewModel.changeUiState(statesList[currentIndex + 1])
//            }
//        }
//    }
//
//    Column(Modifier.fillMaxSize().pointerInput(detailState) {
//        detectHorizontalDragGestures { change, dragAmount ->
//            change.consume() // evitar propagación del gesto
//            onSwipe(dragAmount)
//        }
//    }) {
//        MatchDetailHeader(localTeam, visitorTeam) { state.navigateBack() }
//        MatchDetailInformationBar(
//            state = detailState,
//            onDetailsClicked = { viewModel.changeUiState(DETAILS) },
//            onLineUpsClicked = { viewModel.changeUiState(LINEUPS) },
//            onStatsClicked = { viewModel.changeUiState(STATS) }
//        )
//        AnimatedContent(
//            targetState = detailState,
//            modifier = Modifier.weight(1f).fillMaxWidth(),
//            transitionSpec = {
//                if (direction > 0) {
//                    slideInHorizontally { fullWidth -> fullWidth } with
//                            slideOutHorizontally { fullWidth -> -fullWidth }
//                } else {
//                    slideInHorizontally { fullWidth -> -fullWidth } with
//                            slideOutHorizontally { fullWidth -> fullWidth }
//                }
//            },
//            label = "DetailStateAnimation"
//        ) { target ->
//            when (target) {
//                LOADING -> { /* Loading */ }
//                DETAILS -> MatchDetailDetailsScreen()
//                LINEUPS -> MatchDetailLineUpsScreen(Modifier.weight(1f))
//                STATS -> MatchDetailStatsScreen()
//            }
//        }
//    }
//}
