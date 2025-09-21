package com.gbmultiplatform.domain.firebase

import com.gbmultiplatform.domain.model.player.PlayerInformationModel

interface IPlayersInformationRepository {
    companion object {
        const val SEASON = "2025"
        const val PLAYERS = "players"
        const val INFORMATION = "information"
    }
    suspend fun fetchAllPlayersInformation(): List<PlayerInformationModel>
    suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean
    suspend fun insertPlayerStats(playerId: String): Boolean
    suspend fun insertPlayerImage(playerId: String, image: ByteArray?, isFace: Boolean): String
}