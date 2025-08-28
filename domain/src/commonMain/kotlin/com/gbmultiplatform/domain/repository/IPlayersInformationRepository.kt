package com.gbmultiplatform.domain.repository

import com.gbmultiplatform.domain.model.player.PlayerInformationModel

interface IPlayersInformationRepository {
    suspend fun fetchAllPlayers(): List<PlayerInformationModel>
    suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean
    suspend fun insertPlayerStats(playerId: String): Boolean
}