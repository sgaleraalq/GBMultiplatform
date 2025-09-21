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

package com.gbmultiplatform.presentation.screens.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbmultiplatform.domain.model.player.IPlayerProvider
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.usecase.FetchAllPlayersInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamViewModel(
    playerProvider: IPlayerProvider,
    private val fetchAllPlayersInformation: FetchAllPlayersInformation
) : ViewModel() {
    private val _players = MutableStateFlow<List<PlayerInformationModel>>(emptyList())
    val players = _players

    init {
        viewModelScope.launch {
            val playersList = withContext(Dispatchers.IO) {
                fetchAllPlayersInformation()
            }
            _players.value =
                (playerProvider.providePlayerInformationList() + playersList).sortedBy { it.dorsal }
        }
    }
}
