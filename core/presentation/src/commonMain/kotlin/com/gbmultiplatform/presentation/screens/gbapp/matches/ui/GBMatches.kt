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

package com.gbmultiplatform.presentation.screens.gbapp.matches.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.model.team.MatchModel
import com.gbmultiplatform.model.team.MatchResult
import com.gbmultiplatform.model.team.MatchResult.VICTORY
import com.gbmultiplatform.model.team.TeamModel

@Composable
fun MatchesList(
    modifier: Modifier,
    matches: List<MatchModel>,
    appTeam: TeamModel
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            items = matches,
            key = { match -> match.id }
        ) { match ->
            GBMatch(
                matchResult = VICTORY,
                match = match
            )
        }
    }
}


@Composable
fun GBMatch(
    matchResult: MatchResult,
    match: MatchModel
){
    Card {
        Column {
            MatchDate()
            MatchResult()
        }
    }
}

@Composable
fun MatchDate() {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = "01/01/2025",
        alignment = Start
    )
}

@Composable
fun MatchResult() {
    Row {
        LeftTeam(
            modifier = Modifier.weight(1f)
        )
        GBText(
            modifier = Modifier.fillMaxHeight(),
            text = ":",
            alignment = Center
        )
        RightTeam(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LeftTeam(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxHeight().background(Blue)
    )
}

@Composable
fun RightTeam(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxHeight().background(Red)
    )
}
