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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.model.team.MatchModel
import com.gbmultiplatform.model.team.TeamModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MatchesScreen(
    viewModel: MatchesViewModel = koinViewModel<MatchesViewModel>()
) {
    Column {
        MatchesScreenHeader(
            appTeam = viewModel.provideRandomTeam()
        )
        MatchesList(
            modifier = Modifier.weight(1f).background(Yellow),
            matches = emptyList()
        )
    }
}

@Composable
fun MatchesScreenHeader(
    appTeam: TeamModel
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Bottom,
        horizontalArrangement = spacedBy(24.dp)
    ) {
        /**
         * Team logo
         */
        GBImage(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(50))
                .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
            image = appTeam.logo
        )

        /**
         * Team name
         */
        GBText(
            modifier = Modifier.weight(1f).padding(bottom = 4.dp),
            text = appTeam.name,
            alignment = Start,
            textColor = White,
            style = MaterialTheme.typography.headlineSmall
        )
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
