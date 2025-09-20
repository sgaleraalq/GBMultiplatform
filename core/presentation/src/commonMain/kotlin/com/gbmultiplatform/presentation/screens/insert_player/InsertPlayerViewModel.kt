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
import com.gbmultiplatform.domain.model.player.Position
import com.gbmultiplatform.domain.usecase.InsertNewPlayer
import com.gbmultiplatform.domain.usecase.ShowCamera
import com.gbmultiplatform.domain.usecase.ShowGallery
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.IToastManager
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.CameraState.BODY
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.CameraState.FACE
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.DEFAULT
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.LOADING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertPlayerViewModel(
    private val toastManager: IToastManager,
    private val showCameraUseCase: ShowCamera,
    private val showGalleryUseCase: ShowGallery,
    private val insertNewPlayerUseCase: InsertNewPlayer
) : ViewModel() {

    enum class InsertPlayerState { LOADING, DEFAULT, DORSAL, POSITION }
    enum class CameraState { FACE, BODY }
    val state = MutableStateFlow(LOADING)
    fun changeState(newState: InsertPlayerState) {
        state.value = newState
    }

    private val _player = MutableStateFlow(
        PlayerInformationModel(
            name = "",
            dorsal = 0,
            faceImage = "",
            bodyImage = "",
            position = null
        )
    )
    val player = _player

    private val _faceImage = MutableStateFlow<CommonImage?>(null)
    val faceImage = _faceImage
    private val _bodyImage = MutableStateFlow<CommonImage?>(null)
    val bodyImage = _bodyImage

    private val _availableDorsals = MutableStateFlow<List<Int>>(emptyList())
    val dorsals = _availableDorsals

    private val _useSameImage = MutableStateFlow(false)
    val useSameImage = _useSameImage

    private var imageSelected = FACE
    fun updateImageSelected(newState: CameraState) {
        imageSelected = newState
    }

    init {
        // TODO
        viewModelScope.launch {
            _availableDorsals.value = withContext(Dispatchers.IO) {
                (1..99).toList()
            }
            state.value = DEFAULT
        }
    }

    fun changePlayerName(name: String) {
        _player.value = _player.value.copy(name = name)
    }

    fun updateDorsal(dorsal: Int) {
        _player.value = _player.value.copy(dorsal = dorsal)
    }

    fun updatePosition(position: Position) {
        _player.value = _player.value.copy(position = position)
    }

    fun updatePicture(image: CommonImage?) {
        viewModelScope.launch {
            when (imageSelected) {
                FACE -> updateFaceImage(image)
                BODY -> updateBodyImage(image)
            }
        }
    }

    private fun updateFaceImage(image: CommonImage?) {
        _faceImage.value = image
    }

    private fun updateBodyImage(image: CommonImage?) {
        _bodyImage.value = image
    }

    fun updateUseSameImage() {
        _useSameImage.value = !_useSameImage.value
        when (_useSameImage.value) {
            true -> updateSameImage()
            false -> removeSameImage()
        }
    }

    private fun updateSameImage() {
        if (_faceImage.value != null) {
            _bodyImage.value = _faceImage.value
        } else if (_bodyImage.value != null) {
            _faceImage.value = _bodyImage.value
        }
    }

    private fun removeSameImage() {
        if (imageSelected == FACE) {
            _bodyImage.value = null
        } else if (imageSelected == BODY) {
            _faceImage.value = null
        }
    }

    fun initCamera(
        permissionDeniedMsg: String,
        launchCamera: () -> Unit
    ) {
        viewModelScope.launch {
            showCameraUseCase(
                onLaunchCamera = { launchCamera() },
                onPermissionsDenied = { showToast(permissionDeniedMsg) }
            )
        }
    }
    fun initGallery(
        launchGallery: () -> Unit,
        permissionDeniedMsg: String
    ) {
        viewModelScope.launch {
            showGalleryUseCase(
                onLaunchGallery = { launchGallery() },
                onPermissionsDenied = { showToast(permissionDeniedMsg) }
            )
        }
    }

    fun insertNewPlayer(
        onSuccess: () -> Unit,
        uploadErrorMsg: String,
        notValidPlayerMsg: String
    ) {
        if (!validPlayer()) {
            showToast(notValidPlayerMsg)
            return
        }

        viewModelScope.launch {
            changeState(LOADING)

            val newPlayerInserted = withContext(Dispatchers.IO) {
                insertNewPlayerUseCase(
                    player = _player.value,
                    faceImg = _faceImage.value!!,
                    bodyImg = _bodyImage.value!!
                )
            }

            if (newPlayerInserted) {
                onSuccess()
            } else {
                showToast(uploadErrorMsg)
                changeState(DEFAULT)
            }
        }
    }
    private fun validPlayer(): Boolean {
        return _player.value.let {
            it.name.isNotEmpty() &&
            it.dorsal > 0 &&
            it.position != null
        } && (_faceImage.value != null && _bodyImage.value != null)
    }

    private fun showToast(msg: String) {
        toastManager.showToast(msg)
    }
}
