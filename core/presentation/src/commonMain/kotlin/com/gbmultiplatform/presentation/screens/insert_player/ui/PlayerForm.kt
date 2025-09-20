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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerUi

@Composable
internal fun PlayerForm(
    ui: InsertPlayerUi,
    imageLoader: SharedImagesBridge,
    onNameChanged: (String) -> Unit,
    onDorsalClick: () -> Unit,
    onPositionClick: () -> Unit,
    onFaceClick: () -> Unit,
    onBodyClick: () -> Unit,
    onUseSameImageClick: () -> Unit,
    onShowMediaOrCamera: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = spacedBy(8.dp)
    ) {
        MainInformation(
            playerName = ui.player.name,
            dorsal = ui.player.dorsal,
            position = ui.player.position,
            onPlayerNameChanged = onNameChanged,
            onDorsalClicked = onDorsalClick,
            onPositionClicked = onPositionClick
        )
        Spacer(Modifier.height(16.dp))
        InsertPlayerImages(
            faceImg = ui.faceImage,
            bodyImg = ui.bodyImage,
            useSameImage = ui.useSameImage,
            onFaceClicked = onFaceClick,
            onBodyClicked = onBodyClick,
            onUseSameImageClicked = onUseSameImageClick,
            showMediaOrCamera = onShowMediaOrCamera,
            imageLoader = imageLoader
        )
    }
}
