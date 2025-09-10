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

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBAppTopBar
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.design_system.components.GBMediaOrCamera
import com.gbmultiplatform.design_system.components.GBProgressDialog
import com.gbmultiplatform.domain.utils.CameraManagerCompose
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import com.gbmultiplatform.domain.utils.rememberGalleryManager
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.CameraState.BODY
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.CameraState.FACE
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.CameraState.NONE
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.DEFAULT
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.DORSAL
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.LOADING
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerViewModel.InsertPlayerState.POSITION
import com.gbmultiplatform.presentation.screens.insert_player.ui.DorsalDialog
import com.gbmultiplatform.presentation.screens.insert_player.ui.InsertPlayerImages
import com.gbmultiplatform.presentation.screens.insert_player.ui.MainInformation
import com.gbmultiplatform.presentation.screens.insert_player.ui.PositionDialog
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.insert_new_player
import gbmultiplatform.core.presentation.generated.resources.insert_player
import gbmultiplatform.core.presentation.generated.resources.not_valid_player_to_insert
import gbmultiplatform.core.presentation.generated.resources.permission_denied_camera
import gbmultiplatform.core.presentation.generated.resources.permission_denied_gallery
import gbmultiplatform.core.presentation.generated.resources.select_media_from
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun InsertPlayerScreen(
    viewModel: InsertPlayerViewModel = koinViewModel<InsertPlayerViewModel>()
) {
    val player by viewModel.player.collectAsState()
    val faceImage by viewModel.faceImage.collectAsState()
    val bodyImage by viewModel.bodyImage.collectAsState()
    val state by viewModel.state.collectAsState()
    val dorsals by viewModel.dorsals.collectAsState()

    val imageLoader = getKoin().get<SharedImagesBridge>()
    var showMediaOrCamera by remember { mutableStateOf(false) }
    val permissionDeniedCamera = stringResource(Res.string.permission_denied_camera)
    val permissionDeniedGallery = stringResource(Res.string.permission_denied_gallery)
    val notValidPlayerMsg = stringResource(Res.string.not_valid_player_to_insert)

//    val cameraManager = rememberCameraManager { commonImage ->
//        showMediaOrCamera = false
//        viewModel.updatePicture(commonImage)
//    }
    var showCamera by remember { mutableStateOf(false) }

    val galleryManager = rememberGalleryManager { commonImage ->
        showMediaOrCamera = false
        viewModel.updatePicture(commonImage)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GBAppTopBar(topBarText = stringResource(Res.string.insert_new_player))

        if (state != LOADING) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = spacedBy(8.dp)
            ) {
                MainInformation(
                    playerName = player.name,
                    dorsal = player.dorsal,
                    position = player.position,
                    onPlayerNameChanged = { name -> viewModel.changePlayerName(name) },
                    onDorsalClicked = { viewModel.changeState(DORSAL) },
                    onPositionClicked = { viewModel.changeState(POSITION) }
                )
                Spacer(Modifier.height(16.dp))
                InsertPlayerImages(
                    faceImg = faceImage,
                    bodyImg = bodyImage,
                    onFaceClicked = { viewModel.updateImageSelected(FACE) },
                    onBodyClicked = { viewModel.updateImageSelected(BODY) },
                    showMediaOrCamera = { showMediaOrCamera = true },
                    imageLoader = imageLoader
                )
            }
            Spacer(Modifier.weight(1f))
            InsertPlayerButton {
                viewModel.insertNewPlayer(
                    onSuccess = { println("Player inserted successfully") },
                    onFailure = { println("Failed to insert player") },
                    notValidPlayerMsg = notValidPlayerMsg
                )
            }
        }
    }

    if (showMediaOrCamera) {
        GBMediaOrCamera(
            title = stringResource(Res.string.select_media_from),
            dismiss = {
                viewModel.updateImageSelected(NONE)
                showMediaOrCamera = false
            },
            onMediaClicked = {
                viewModel.initGallery(
                    launchGallery = { galleryManager.launch() },
                    permissionDeniedMsg = permissionDeniedGallery
                )
            },
            onCameraClicked = {
                viewModel.initCamera(
                    launchCamera = {
                        showMediaOrCamera = false
                        showCamera = true
                    },
                    permissionDeniedMsg = permissionDeniedCamera
                )
            }
        )
    }

    if (showCamera) {
        CameraManagerCompose {
            showCamera = false
            viewModel.updatePicture(it)
        }
    }

    when (state) {
        DEFAULT -> {}
        LOADING -> {
            Box(Modifier.fillMaxSize()) { GBProgressDialog(true, White) }
        }

        DORSAL -> {
            DorsalDialog(
                show = true,
                dorsals = dorsals,
                onDorsalClicked = { viewModel.updateDorsal(it) },
                dismiss = { viewModel.changeState(DEFAULT) }
            )
        }

        POSITION -> {
            PositionDialog(
                show = true,
                onPositionClicked = { viewModel.updatePosition(it) },
                dismiss = { viewModel.changeState(DEFAULT) }
            )
        }
    }
}

@Composable
fun InsertPlayerButton(
    onInsert: () -> Unit
) {
    GBElevatedButton(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        text = stringResource(Res.string.insert_player),
        onClick = { onInsert() }
    )
}
