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

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.model.team.MatchModel
import com.gbmultiplatform.model.team.MatchResult
import com.gbmultiplatform.model.team.TeamModel
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.unknown_location
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchesList(
    modifier: Modifier,
    matches: List<MatchModel>,
    appTeam: TeamModel,
//    matchResult: (MatchModel, TeamModel) -> MatchResult
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        verticalArrangement = spacedBy(12.dp)
    ) {
        items(
            items = matches,
            key = { match -> match.id }
        ) { match ->
            val randomResult = MatchResult.entries.toTypedArray().random()
            GBMatch(
                matchResult = randomResult,
                match = match
            )
        }
    }
}

@Composable
fun GBMatch(
    matchResult: MatchResult,
    match: MatchModel,
    location: String? = null
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = matchResult.color
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = spacedBy(12.dp)
        ) {
            MatchHeader()
            MatchResult(match)
            MatchLocation(location)
        }
    }
}

@Composable
fun MatchHeader() {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalAlignment = CenterVertically
    ) {
        MatchDate(Modifier.weight(1f))
        MatchType()
    }
}

@Composable
fun MatchType(matchType: String = "Journey 1") {
    GBText(
        text = matchType,
        alignment = Start,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun MatchResult(match: MatchModel) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp),
        verticalAlignment = CenterVertically
    ) {
        MatchTeam(
            modifier = Modifier.weight(1f),
            team = match.localTeam,
            goals = match.localGoals.toString()
        )
        GBText(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "-",
            alignment = Center
        )
        MatchTeam(
            modifier = Modifier.weight(1f),
            team = match.visitorTeam,
            goals = match.visitorGoals.toString(),
            isLocal = false
        )
    }
}

@Composable
fun MatchTeam(
    modifier: Modifier,
    team: TeamModel,
    goals: String,
    isLocal: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ) {
        if (!isLocal) {
            GBText(
                text = goals,
                alignment = End,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        GBMatchResultTeam(Modifier.weight(1f), team.logo, team.name)
        if (isLocal) {
            GBText(
                text = goals,
                alignment = Start,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun GBMatchResultTeam(modifier: Modifier, image: String, teamName: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(8.dp)
    ) {
        GBImage(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(50))
                .border(width = 1.dp, color = White, shape = RoundedCornerShape(50)),
            image = image
        )
        GBTeamName(teamName)
    }
}

@Composable
fun GBTeamName(teamName: String) {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = teamName,
        alignment = Center,
        style = MaterialTheme.typography.bodySmall
    )
}

// TODO
@Composable
fun MatchDate(
    modifier: Modifier = Modifier,
    date: String = "01/01/2025"
) {
    GBText(
        modifier = modifier,
        text = date,
        alignment = Start,
        style = MaterialTheme.typography.bodySmall.copy(
            fontStyle = Italic
        )
    )
}

@Composable
fun MatchLocation(location: String?) {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = location ?: stringResource(Res.string.unknown_location),
        alignment = Center,
        style = MaterialTheme.typography.bodySmall
    )
}
