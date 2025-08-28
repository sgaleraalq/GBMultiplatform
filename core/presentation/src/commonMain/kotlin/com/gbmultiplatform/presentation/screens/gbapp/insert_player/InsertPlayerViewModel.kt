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

package com.gbmultiplatform.presentation.screens.gbapp.insert_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbmultiplatform.data.network.firebase.IPlayersInformationFirebase
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.Position.FORWARD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertPlayerViewModel(
    private val firebaseRepository: IPlayersInformationFirebase
): ViewModel() {

    fun insertNewPlayer(
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        val newPlayer = PlayerInformationModel(
            id = "",
            name = "New Player",
            image = "https://example.com/image.png",
            dorsal = 1,
            position = FORWARD
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val newPlayerInserted = firebaseRepository.insertNewPlayer(newPlayer)
                if (newPlayerInserted) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
        }
    }
}