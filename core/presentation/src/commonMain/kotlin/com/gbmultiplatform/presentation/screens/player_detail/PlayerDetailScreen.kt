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

package com.gbmultiplatform.presentation.screens.player_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBBackButton
import com.gbmultiplatform.design_system.style.gb_text_field_label_color
import com.gbmultiplatform.presentation.screens.player_detail.ui.PlayerDetailImage
import com.gbmultiplatform.presentation.screens.player_detail.ui.PlayerDetailInformationBox
import org.koin.compose.viewmodel.koinViewModel

internal const val LOGO_SIZE = 50

@Composable
fun PlayerDetailScreen(
    playerId: String,
    navigateBack: () -> Unit,
    viewModel: PlayerDetailViewModel = koinViewModel<PlayerDetailViewModel>()
) {
    val playerStats by viewModel.playerStats.collectAsState()
    val playerInformation by viewModel.playerInformation.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadPlayerInformation(playerId)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        PlayerDetailImage(playerInformation?.bodyImage)
        PlayerDetailInformationBox(
            modifier = Modifier.align(BottomCenter),
            playerName = playerInformation?.name,
            playerStats = playerStats
        )


        GBBackButton(
            modifier = Modifier
                .align(TopStart)
                .padding(12.dp).background(
                    color = White,
                    shape = RoundedCornerShape(50)
                ),
            color = gb_text_field_label_color
        ) { navigateBack() }
    }
}
