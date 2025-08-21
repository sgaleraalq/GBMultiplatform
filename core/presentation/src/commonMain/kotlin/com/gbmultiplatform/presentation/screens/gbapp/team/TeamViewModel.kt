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

package com.gbmultiplatform.presentation.screens.gbapp.team

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.domain.model.player.IPlayerProvider
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.helper.GazteluBiraUtils
import kotlinx.coroutines.flow.MutableStateFlow

class TeamViewModel(
    playerProvider: IPlayerProvider
): ViewModel() {
    val players = MutableStateFlow<List<PlayerInformationModel>>(emptyList())
    val appTeam = GazteluBiraUtils.GAZTELU_BIRA


    init {
        players.value = playerProvider.providePlayerInformationList()
    }

}