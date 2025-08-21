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

package com.gbmultiplatform.presentation.screens.gbapp.stats

import androidx.lifecycle.ViewModel
import com.gbmultiplatform.domain.model.player.IPlayerProvider
import com.gbmultiplatform.domain.model.player.PlayerModel
import com.gbmultiplatform.domain.model.player.Stat
import com.gbmultiplatform.domain.model.player.Stat.ASSISTS
import com.gbmultiplatform.domain.model.player.Stat.CLEAN_SHEETS
import com.gbmultiplatform.domain.model.player.Stat.GAMES_PLAYED
import com.gbmultiplatform.domain.model.player.Stat.GOALS
import com.gbmultiplatform.domain.model.player.Stat.PENALTIES_PROVOKED
import com.gbmultiplatform.domain.model.player.Stat.PERCENTAGE
import com.gbmultiplatform.domain.model.player.Stat.RED_CARDS
import com.gbmultiplatform.domain.model.player.Stat.SAVES
import com.gbmultiplatform.domain.model.player.Stat.YELLOW_CARDS
import kotlinx.coroutines.flow.MutableStateFlow

class StatsViewModel(
    private val playerProvider: IPlayerProvider
) : ViewModel() {

    data class PlayerDisplayStats(
        val id: String,
        val name: String,
        val image: String,
        val stat: Double
    )

    private val _playersData = MutableStateFlow<List<PlayerModel>>(emptyList())
    private val _selectedStat = MutableStateFlow(PERCENTAGE)
    val selectedStat = _selectedStat
    private val _players: MutableStateFlow<List<PlayerDisplayStats>> = MutableStateFlow(emptyList())
    val players = _players

    private val _selectedPlayer = MutableStateFlow<PlayerModel?>(null)
    val selectedPlayer = _selectedPlayer

    init {
        _playersData.value = playerProvider.providePlayerList()
        updateDisplayedStats()
    }

    fun changeSelectedStat(stat: Stat) {
        _selectedStat.value = stat
        updateDisplayedStats()
    }

    fun unselectPlayer() {
        _selectedPlayer.value = null
    }

    fun selectPlayer(playerId: String) {
        _selectedPlayer.value = _playersData.value.find { it.id == playerId }
    }

    private fun updateDisplayedStats() {
        _players.value = _playersData.value.map { player ->
            val statValue = when (_selectedStat.value) {
                GOALS -> player.goals.toDouble()
                ASSISTS -> player.assists.toDouble()
                PENALTIES_PROVOKED -> player.penaltiesProvoked.toDouble()
                CLEAN_SHEETS -> player.cleanSheets.toDouble()
                SAVES -> player.saves.toDouble()
                RED_CARDS -> player.redCards.toDouble()
                YELLOW_CARDS -> player.yellowCards.toDouble()
                GAMES_PLAYED -> player.gamesPlayed.toDouble()
                PERCENTAGE -> player.percentage
            }
            PlayerDisplayStats(
                id = player.id,
                name = player.name,
                image = player.image,
                stat = statValue
            )
        }.sortedByDescending { it.stat }
    }
}
