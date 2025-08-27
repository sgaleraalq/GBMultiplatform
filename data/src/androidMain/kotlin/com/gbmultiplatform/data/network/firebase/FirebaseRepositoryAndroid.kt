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

import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepositoryAndroid() : IFirebase {

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val SEASON = "2025"
        const val PLAYERS = "players"
        const val INFORMATION = "information"
    }

    override suspend fun insertNewPlayer(player: PlayerInformationModel): Boolean {
        return firestore
            .collection(SEASON)
            .document(PLAYERS)
            .collection(INFORMATION)
            .document(player.id)
            .set(player)
            .isSuccessful
    }
}