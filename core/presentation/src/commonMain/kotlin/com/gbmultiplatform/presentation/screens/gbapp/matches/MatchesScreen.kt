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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.model.team.MatchModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = koinViewModel<MatchesViewModel>()
) {
    Column {
        MatchesScreenHeader()
        MatchesList(
            modifier = Modifier.weight(1f).background(Yellow),
            matches = emptyList()
        )
    }
}

@Composable
fun MatchesScreenHeader() {
    Row(
        modifier = Modifier.height(50.dp).fillMaxWidth().background(Red)
    ) {

    }
}


@Composable
fun MatchesList(
    modifier: Modifier,
    matches: List<MatchModel>
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(matches) {

        }
    }
}
