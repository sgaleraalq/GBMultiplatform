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

package com.gbmultiplatform.data.network.firebase

import android.util.Log
import com.gbmultiplatform.data.mappers.PlayerInformationMapper.asResponse
import com.gbmultiplatform.data.mappers.asModel
import com.gbmultiplatform.data.network.response.PlayerInformationResponse
import com.gbmultiplatform.domain.firebase.IPlayersInformation
import com.gbmultiplatform.domain.firebase.IPlayersInformation.Companion.INFORMATION
import com.gbmultiplatform.domain.firebase.IPlayersInformation.Companion.PLAYERS
import com.gbmultiplatform.domain.firebase.IPlayersInformation.Companion.SEASON
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await

class PlayerInformationAndroid() : IPlayersInformation {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storage: FirebaseStorage = Firebase.storage

    override suspend fun fetchPlayerInformation(playerId: String) =
        firestore.collection(SEASON).document(PLAYERS).collection(INFORMATION).document(playerId).get()
            .await().toObject(PlayerInformationResponse::class.java)?.asModel()

    override suspend fun fetchAllPlayersInformation() =
        firestore.collection(SEASON).document(PLAYERS).collection(INFORMATION).get().await()
            .toObjects(PlayerInformationResponse::class.java).asModel()

    override suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean {
        val playerResponse = asResponse(player)
        return try {
            firestore
                .collection(SEASON)
                .document(PLAYERS)
                .collection(INFORMATION)
                .document(playerResponse.id)
                .set(playerResponse)
                .await()
            true
        } catch (e: Exception) {
            Log.e("Firebase", "Error adding document", e)
            false
        }
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
                .child("$SEASON/$PLAYERS/$playerId/${if (isFace) "face" else "body"}.jpg")

            reference.putBytes(image).await()
            reference.downloadUrl.await().toString()
        } else {
            ""
        }
    }
}