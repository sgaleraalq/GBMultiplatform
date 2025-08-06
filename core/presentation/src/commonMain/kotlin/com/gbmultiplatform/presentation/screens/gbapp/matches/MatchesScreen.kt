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

package com.gbmultiplatform.presentation.screens.gbapp.matches

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gbmultiplatform.presentation.screens.gbapp.matches.ui.MatchesList
import com.gbmultiplatform.presentation.screens.gbapp.matches.ui.MatchesScreenHeader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = koinViewModel<MatchesViewModel>()
) {
    Column {
        MatchesScreenHeader(
            appTeam = viewModel.appTeam
        )
        MatchesList(
            modifier = Modifier.weight(1f),
            matches = viewModel.provideMatches(),
            matchResult = { match -> viewModel.calculateResult(match) }
        )
    }
}
