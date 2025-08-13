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

package com.gbmultiplatform.presentation.screens.gbapp.match_detail

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.model.team.ITeamProvider
import com.gbmultiplatform.model.team.TeamModel
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LINEUPS
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.MatchDetailViewModel.MatchDetailState.LOADING
import com.gbmultiplatform.presentation.screens.gbapp.match_detail.ui.TeamDetailModel
import kotlinx.coroutines.flow.MutableStateFlow

class MatchDetailViewModel(
    private val teamProvider: ITeamProvider
) : ViewModel() {

    enum class MatchDetailState {
        LOADING, DETAILS, LINEUPS, STATS
    }

    private val _state = MutableStateFlow(LOADING)
    val state = _state

    init {
        changeUiState(LINEUPS)
    }

    fun changeUiState(state: MatchDetailState) {
        if (_state.value != state) {
            _state.value = state
        }
    }

    val localTeam = TeamDetailModel(
        team = TeamModel(
            "gaztelu_bira",
            "Gaztelu Bira",
            "https://firebasestorage.googleapis.com/v0/b/gbmultiplatform.firebasestorage.app/o/img_gaztelu_bira.webp?alt=media&token=3708f1c5-f9d7-4353-8829-967b21df75ed"
        ),
        score = 1,
        isLocal = true
    )

    val visitorTeam = TeamDetailModel(
        team = TeamModel(
            "non_gaztelu",
            "Random Bira",
            "https://static.vecteezy.com/system/resources/previews/011/049/345/non_2x/soccer-football-badge-logo-sport-team-identity-illustrations-isolated-on-white-background-vector.jpg"
        ),
        score = 1,
        isLocal = false
    )
}