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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.domain.usecase.FetchPlayerInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerDetailViewModel(
    private val fetchPlayerInformation: FetchPlayerInformation
) : ViewModel() {
    private val _playerInformation = MutableStateFlow<PlayerInformationModel?>(null)
    val playerInformation = _playerInformation

    private val _playerStats = MutableStateFlow<PlayerStatsModel?>(null)
    val playerStats = _playerStats

    fun loadPlayerInformation(playerId: String) {
        viewModelScope.launch {
            _playerInformation.value = withContext(Dispatchers.IO) {
                fetchPlayerInformation(playerId)
            }
//            _playerStats.value = withContext(Dispatchers.IO) {
//                _playerInformation.value?.stats
//            }
            println(
                "PlayerInformationViewModel loadPlayerInformation: ${_playerInformation.value}, stats: ${_playerStats.value}"
            )
        }
    }
}
