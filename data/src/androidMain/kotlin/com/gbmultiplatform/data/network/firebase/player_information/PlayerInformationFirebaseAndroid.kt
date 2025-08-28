package com.gbmultiplatform.data.network.firebase.player_information

import com.gbmultiplatform.data.network.response.PlayerInformationResponse
import com.gbmultiplatform.data.network.firebase.IPlayersInformationFirebase
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.google.firebase.firestore.FirebaseFirestore

class PlayerInformationFirebaseAndroid() : IPlayersInformationFirebase {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val SEASON = "2025"
        const val PLAYERS = "players"
        const val INFORMATION = "information"
    }

    override suspend fun fetchAllPlayers(): List<PlayerInformationModel> {
        return emptyList() // TODO
    }

    override suspend fun insertNewPlayer(player: PlayerInformationResponse): Boolean {
        return firestore
            .collection(SEASON)
            .document(PLAYERS)
            .collection(INFORMATION)
            .document(player.id)
            .set(player)
            .isSuccessful
    }
}