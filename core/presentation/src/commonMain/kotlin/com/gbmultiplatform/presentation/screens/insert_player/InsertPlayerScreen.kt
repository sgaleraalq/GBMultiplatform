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

package com.gbmultiplatform.presentation.screens.insert_player

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBAppTopBar
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.presentation.screens.insert_player.ui.InsertPlayerImages
import com.gbmultiplatform.presentation.screens.insert_player.ui.MainInformation
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.insert_new_player
import gbmultiplatform.core.presentation.generated.resources.insert_player
import gbmultiplatform.core.presentation.generated.resources.not_valid_player_to_insert
import gbmultiplatform.core.presentation.generated.resources.permission_denied_camera
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InsertPlayerScreen(
    viewModel: InsertPlayerViewModel = koinViewModel<InsertPlayerViewModel>()
) {
    val player by viewModel.player.collectAsState()
    val permissionDeniedCamera = stringResource(Res.string.permission_denied_camera)
    val notValidPlayerMsg = stringResource(Res.string.not_valid_player_to_insert)

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = spacedBy(8.dp)
    ) {
        GBAppTopBar(topBarText = stringResource(Res.string.insert_new_player))
        MainInformation(
            playerName = player.name,
            onPlayerNameChanged = { name -> viewModel.changePlayerName(name) }
        )
        Spacer(Modifier.height(16.dp))
        InsertPlayerImages(
            faceImg = player.faceImage,
            bodyImg = player.bodyImage,
            initCamera = {
                viewModel.initCamera(
                    permissionDeniedMsg = permissionDeniedCamera
                )
            }
        )
        Spacer(Modifier.weight(1f))
        InsertPlayerButton {
            viewModel.insertNewPlayer(
                onSuccess = { println("Player inserted successfully") },
                onFailure = { println("Failed to insert player") },
                notValidPlayerMsg = notValidPlayerMsg
            )
        }
    }
}

@Composable
fun InsertPlayerButton(
    onInsert: () -> Unit
) {
    GBElevatedButton(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        text = stringResource(Res.string.insert_player),
        onClick = { onInsert() }
    )
}
