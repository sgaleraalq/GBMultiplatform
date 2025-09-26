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

package com.gbmultiplatform.presentation.screens.home.tabs.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gbmultiplatform.design_system.components.GBAppTopBar
import com.gbmultiplatform.presentation.navigation.Destination.MatchDetail
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.home.tabs.matches.ui.MatchesList

@Composable
fun MatchesScreenUI(
    state: NavigationState, viewModel: MatchesViewModel
) {
    Column {
        GBAppTopBar()
        MatchesList(
            modifier = Modifier.weight(1f),
            matches = viewModel.provideMatches(),
            matchResult = { match -> viewModel.calculateResult(match) },
            onMatchClicked = { matchId ->
                state.navigateTo(MatchDetail)
            }
        )
    }
}
