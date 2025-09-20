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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.utils.CommonImage

data class InsertPlayerUi(
    val player: PlayerInformationModel,
    val state: InsertPlayerViewModel.InsertPlayerState,
    val dorsals: List<Int>,
    val faceImage: CommonImage?,
    val bodyImage: CommonImage?,
    val useSameImage: Boolean
)

@Composable
internal fun InsertPlayerViewModel.collectUi(): State<InsertPlayerUi> {
    val player by player.collectAsState()
    val state by state.collectAsState()
    val dorsals by dorsals.collectAsState()
    val faceImage by faceImage.collectAsState()
    val bodyImage by bodyImage.collectAsState()
    val useSameImage by useSameImage.collectAsState()
    return remember(player, state, dorsals, faceImage, bodyImage, useSameImage) {
        mutableStateOf(
            InsertPlayerUi(player, state, dorsals, faceImage, bodyImage, useSameImage)
        )
    }
}
