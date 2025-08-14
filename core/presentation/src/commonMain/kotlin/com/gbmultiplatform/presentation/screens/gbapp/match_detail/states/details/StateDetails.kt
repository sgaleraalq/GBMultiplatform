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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.model.team.TeamModel
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.ic_date
import gbmultiplatform.core.presentation.generated.resources.ic_location
import gbmultiplatform.core.presentation.generated.resources.no_description_provided
import gbmultiplatform.core.presentation.generated.resources.no_location_available
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchDetailStateDetails(
    modifier: Modifier,
    detailsInfo: DetailsTeamModel
) {
    val scrollState = rememberScrollState()
    Column(
        modifier.padding(horizontal = 12.dp).padding(top = 12.dp, bottom = 24.dp),
        verticalArrangement = spacedBy(12.dp)
    ) {
        DetailsTitle(detailsInfo.local, detailsInfo.visitor)
        DetailsDate(detailsInfo.date)
        DetailsDescription(
            modifier = Modifier.weight(1f).verticalScroll(scrollState),
            description = detailsInfo.description
        )
        DetailsLocation(detailsInfo.location)
    }
}

@Composable
fun DetailsDate(
    date: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DetailsIcon(Res.drawable.ic_date)
        Spacer(Modifier.width(8.dp))
        GBText(
            text = date,
            style = gBTypography().bodySmall.copy(
                fontStyle = Italic
            )
        )
    }
}

@Composable
fun DetailsTitle(
    local: TeamModel,
    visitor: TeamModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {
        GBText(
            modifier = Modifier.weight(1f),
            text = local.name,
            style = gBTypography().titleMedium.copy(
                fontWeight = Bold
            ),
            alignment = Center
        )
        GBText(
            text = "-",
            style = gBTypography().titleLarge.copy(
                fontWeight = Bold
            )
        )
        GBText(
            modifier = Modifier.weight(1f),
            text = visitor.name,
            style = gBTypography().titleMedium.copy(
                fontWeight = Bold
            ),
            alignment = Center
        )
    }
}

@Composable
fun DetailsDescription(
    modifier: Modifier,
    description: String?
) {
    GBText(
        modifier = modifier.fillMaxWidth().padding(12.dp),
        text = description ?: stringResource(Res.string.no_description_provided),
        style = gBTypography().bodyMedium,
        alignment = Justify
    )
}

@Composable
fun DetailsLocation(
    location: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(8.dp)
    ) {
        Spacer(Modifier.weight(1f))
        DetailsIcon(Res.drawable.ic_location)
        GBText(
            text = location ?: stringResource(Res.string.no_location_available),
            style = gBTypography().bodyMedium,
            alignment = End
        )
    }
}

@Composable
fun DetailsIcon(painter: DrawableResource) {
    Icon(
        modifier = Modifier.size(16.dp),
        painter = painterResource(painter),
        contentDescription = null,
        tint = White
    )
}
