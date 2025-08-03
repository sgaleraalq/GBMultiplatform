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
import com.gbmultiplatform.model.player.IPlayerProvider
import com.gbmultiplatform.model.player.Player
import com.gbmultiplatform.model.player.Stats
import com.gbmultiplatform.model.player.Stats.ASSISTS
import com.gbmultiplatform.model.player.Stats.CLEAN_SHEETS
import com.gbmultiplatform.model.player.Stats.GAMES_PLAYED
import com.gbmultiplatform.model.player.Stats.GOALS
import com.gbmultiplatform.model.player.Stats.PENALTIES_PROVOKED
import com.gbmultiplatform.model.player.Stats.PERCENTAGE
import com.gbmultiplatform.model.player.Stats.RED_CARDS
import com.gbmultiplatform.model.player.Stats.SAVES
import com.gbmultiplatform.model.player.Stats.YELLOW_CARDS
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

    private val _playersData = MutableStateFlow<List<Player>>(emptyList())
    private val _selectedStat = MutableStateFlow(PERCENTAGE)
    val selectedStat = _selectedStat
    private val _players: MutableStateFlow<List<PlayerDisplayStats>> = MutableStateFlow(emptyList())
    val players = _players

    private val _selectedPlayer = MutableStateFlow<Player?>(null)
    val selectedPlayer = _selectedPlayer

    init {
        _playersData.value = playerProvider.providePlayerList()
        updateDisplayedStats()
    }

    fun changeSelectedStat(stat: Stats) {
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
