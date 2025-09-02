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

package com.gbmultiplatform.presentation.screens.insert_player.ui

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign.Companion.Start
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.components.GBTextField
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.model.player.Position
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.dorsal
import gbmultiplatform.core.presentation.generated.resources.information
import gbmultiplatform.core.presentation.generated.resources.player_name
import gbmultiplatform.core.presentation.generated.resources.position
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainInformation(
    playerName: String,
    dorsal: Int,
    position: Position?,
    onPlayerNameChanged: (String) -> Unit,
    onDorsalClicked: () -> Unit,
    onPositionClicked: () -> Unit
) {
    GBText(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.information),
        style = gBTypography().titleMedium,
        alignment = Start
    )
    GBTextField(
        modifier = Modifier.fillMaxWidth(),
        text = playerName,
        onTextChanged = { onPlayerNameChanged(it) },
        label = stringResource(Res.string.player_name)
    )
    Spacer(Modifier.height(8.dp))
    DorsalAndPosition(dorsal, position, onDorsalClicked, onPositionClicked)
}

@Composable
fun DorsalAndPosition(
    dorsal: Int,
    position: Position?,
    onDorsalClicked: () -> Unit,
    onPositionClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(8.dp)
    ) {
        InformationComponent(
            modifier = Modifier.weight(1f),
            informationText = if (dorsal == 0) stringResource(Res.string.dorsal) else dorsal.toString()
        ) { onDorsalClicked() }
        InformationComponent(
            modifier = Modifier.weight(1f),
            informationText = position?.name ?: stringResource(Res.string.position)
        ) { onPositionClicked() }
    }
}

@Composable
fun InformationComponent(
    modifier: Modifier,
    informationText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = gray_box_in_black_bg
        ),
        onClick = { onClick() }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            GBText(
                text = informationText
            )
        }
    }
}
