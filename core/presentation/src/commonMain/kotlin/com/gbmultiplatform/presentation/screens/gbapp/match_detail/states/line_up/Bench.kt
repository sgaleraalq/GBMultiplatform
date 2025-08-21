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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.line_up

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.states.RowPlayer
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.bench
import gbmultiplatform.core.presentation.generated.resources.managers
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

fun LazyListScope.managers(managers: List<UIPlayer>) {
    item {
        ManagerHeaderText()
        BenchHorizontalDivider()
        BenchListSpacer()
    }
    items(managers) { manager ->
        RowPlayer(Modifier.background(benchBgColor),manager)
    }
}

fun LazyListScope.benchPlayers(benchPlayers: List<UIPlayer>) {
    item {
        BenchListSpacer(12)
        BenchHeaderText()
        BenchHorizontalDivider()
        BenchListSpacer()
    }
    items(benchPlayers) { player ->
        RowPlayer(Modifier.background(benchBgColor),player)
    }
}

@Composable
fun BenchListSpacer(height: Int = 8) {
    Spacer(
        Modifier.height(height.dp).fillMaxWidth().padding(horizontal = 12.dp)
            .background(benchBgColor)
    )
}

@Composable
fun BenchHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = benchHorizontalPadding),
        thickness = 0.25.dp,
        color = White
    )
}

@Composable
fun ManagerHeaderText() {
    HeaderText(Res.string.managers)
}

@Composable
fun BenchHeaderText() {
    HeaderText(Res.string.bench, false)
}

@Composable
fun HeaderText(text: StringResource, isManager: Boolean = true) {
    GBText(
        modifier = Modifier
            .padding(horizontal = benchHorizontalPadding)
            .background(
                color = benchBgColor,
                shape = RoundedCornerShape(
                    topStart = if (isManager) 12.dp else 0.dp,
                    topEnd = if (isManager) 12.dp else 0.dp
                )
            )
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        text = stringResource(text).uppercase(),
        style = gBTypography().bodyLarge.copy(
            fontWeight = Bold,
            color = White
        )
    )
}
