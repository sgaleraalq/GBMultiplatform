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

package com.gbmultiplatform.presentation.screens.gbapp.matches.detail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBFootballField
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.model.LineUpFormation.FourThreeThree
import com.gbmultiplatform.design_system.model.UIPlayer
import com.gbmultiplatform.design_system.style.gBTypography
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.bench
import gbmultiplatform.core.presentation.generated.resources.managers
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MatchDetailLineUpsScreen(modifier: Modifier) {
    val benchPlayers = listOf(
        UIPlayer("Player 1", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 2", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 3", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 4", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 5", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 6", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 7", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Player 8", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg")
    )
    val managers = listOf(
        UIPlayer("Manager 1", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg"),
        UIPlayer("Manager 2", "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg")
    )
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            GBFootballField(
                modifier = Modifier.padding(8.dp),
                formation = FourThreeThree
            )
        }
        managers(managers)
        benchPlayers(benchPlayers)
    }
}

fun LazyListScope.managers(managers: List<UIPlayer>) {
    item {
        ManagerHeaderText()
    }
    items(managers) { manager ->
        BenchPlayer(manager)
    }
}

fun LazyListScope.benchPlayers(benchPlayers: List<UIPlayer>) {
    item {
        BenchHeaderText()
    }
    items(benchPlayers) { player ->
        BenchPlayer(player)
    }
}

@Composable
fun ManagerHeaderText() {
    HeaderText(Res.string.managers)
}

@Composable
fun BenchHeaderText() {
    HeaderText(Res.string.bench)
}

@Composable
fun HeaderText(text: StringResource) {
    GBText(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 4.dp)
            .background(Gray).fillMaxWidth()
            .padding(horizontal = 12.dp),
        text = stringResource(text).uppercase(),
        style = gBTypography().bodyLarge.copy(
            fontWeight = Bold
        )
    )
}

@Composable
fun BenchPlayer(player: UIPlayer) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = spacedBy(12.dp)
    ) {
        GBImage(
            modifier = Modifier.size(34.dp).clip(RoundedCornerShape(50)),
            image = player.image,
        )
        GBText(player.name)
    }
}
