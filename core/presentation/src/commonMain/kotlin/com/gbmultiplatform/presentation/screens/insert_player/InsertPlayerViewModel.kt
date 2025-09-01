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

package com.gbmultiplatform.presentation.screens.insert_player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.usecase.InsertNewPlayer
import com.gbmultiplatform.domain.usecase.ShowCamera
import com.gbmultiplatform.domain.utils.IToastManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertPlayerViewModel(
    private val toastManager: IToastManager,
    private val showCameraUseCase: ShowCamera,
    private val insertNewPlayerUseCase: InsertNewPlayer
) : ViewModel() {

    private val _player = MutableStateFlow<PlayerInformationModel>(
        PlayerInformationModel(
            id = "",
            name = "",
            bodyImage = "",
            faceImage = "",
            dorsal = 0,
            position = null
        )
    )

    private val _playerName = MutableStateFlow("")
    val playerName = _playerName

    fun initCamera(
        permissionDeniedMsg: String
    ) {
        viewModelScope.launch {
            showCameraUseCase(
                onPermissionsDenied = { showToast(permissionDeniedMsg) }
            )
        }
    }

    fun insertNewPlayer(
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
        notValidPlayerMsg: String
    ) {
        if (validPlayer()) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val newPlayerInserted = insertNewPlayerUseCase(_player.value)
                    if (newPlayerInserted) {
                        onSuccess()
                    } else {
                        onFailure()
                    }
                }
            }
        } else {
            showToast(notValidPlayerMsg)
        }
    }

    private fun validPlayer(): Boolean {
        return _player.value.let {
            it.name.isNotEmpty() && it.dorsal > 0 && it.position != null && it.faceImage.isNotEmpty() && it.bodyImage.isNotEmpty()
        }
    }

    private fun showToast(msg: String) {
        toastManager.showToast(msg)
    }
}