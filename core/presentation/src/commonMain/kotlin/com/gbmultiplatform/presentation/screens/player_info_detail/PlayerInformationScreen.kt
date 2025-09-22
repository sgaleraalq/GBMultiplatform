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

package com.gbmultiplatform.presentation.screens.player_info_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBBackButton
import com.gbmultiplatform.design_system.components.GBImage
import com.gbmultiplatform.design_system.components.GBPlayerImage
import com.gbmultiplatform.design_system.components.GBText
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlayerInformationScreen(
    playerId: String,
    navigateBack: () -> Unit,
    viewModel: PlayerInformationViewModel = koinViewModel<PlayerInformationViewModel>()
) {
    val playerStats by viewModel.playerStats.collectAsState()
    val playerInformation by viewModel.player.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadPlayerInformation(playerId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        GBBackButton(Modifier.align(TopStart)) { navigateBack() }
    }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
        GBText(
            text = playerInformation?.name ?: "",
            textColor = White
        )
        GBPlayerImage(
            modifier = Modifier.size(100.dp),
            image = playerInformation?.faceImage
        )
        GBImage(
            modifier = Modifier.size(100.dp),
            image = playerInformation?.bodyImage
        )
    }
}