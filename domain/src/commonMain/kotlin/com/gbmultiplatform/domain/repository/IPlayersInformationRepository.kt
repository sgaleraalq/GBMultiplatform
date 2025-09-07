package com.gbmultiplatform.domain.repository

import androidx.compose.ui.graphics.ImageBitmap
import com.gbmultiplatform.domain.model.player.PlayerInformationModel

interface IPlayersInformationRepository {
    companion object {
        const val PLAYERS = "players"
        const val INFORMATION = "information"
    }
    suspend fun fetchAllPlayers(): List<PlayerInformationModel>
    suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean
    suspend fun insertPlayerStats(playerId: String): Boolean
    suspend fun insertPlayerImage(playerId: String, image: ByteArray?, isFace: Boolean): String
}