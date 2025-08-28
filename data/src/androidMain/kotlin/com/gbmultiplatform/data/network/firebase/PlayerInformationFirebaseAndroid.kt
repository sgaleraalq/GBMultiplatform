package com.gbmultiplatform.data.network.firebase

import com.gbmultiplatform.data.mappers.PlayerInformationMapper.asResponse
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.google.firebase.firestore.FirebaseFirestore

class PlayerInformationFirebaseAndroid() : IPlayersInformationRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val SEASON = "2025"
        const val PLAYERS = "players"
        const val INFORMATION = "information"
    }

    override suspend fun fetchAllPlayers(): List<PlayerInformationModel> {
        return emptyList() // TODO
    }

    override suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean {
        val playerResponse = asResponse(player)
        return firestore
            .collection(SEASON)
            .document(PLAYERS)
            .collection(INFORMATION)
            .document(playerResponse.id)
            .set(playerResponse)
            .isSuccessful
    }

    override suspend fun insertPlayerStats(playerId: String): Boolean {
        return true
    }
}