package com.gbmultiplatform.data.network.firebase

import com.gbmultiplatform.data.mappers.PlayerInformationMapper.asResponse
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

class PlayerInformationFirebaseAndroid() : IPlayersInformationRepository {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = Firebase.storage

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

    override suspend fun insertPlayerImage(
        playerId: String,
        image: ByteArray?,
        isFace: Boolean
    ): String {
        return if (image != null) {
            val reference = storage.reference
                .child("$SEASON/$playerId/${if (isFace) "face" else "body"}.jpg")

            reference.putBytes(image).await()
            reference.downloadUrl.await().toString()
        } else {
            ""
        }
    }
}