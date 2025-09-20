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

package com.gbmultiplatform.domain.usecase

import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.repository.IPlayersInformationRepository
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.CommonImage.FromFrontCamera
import com.gbmultiplatform.domain.utils.SharedImagesBridge

class InsertNewPlayer(
    private val repository: IPlayersInformationRepository,
    private val imageLoader: SharedImagesBridge
) {
    suspend operator fun invoke(
        player: PlayerInformationModel,
        faceImg: CommonImage,
        bodyImg: CommonImage
    ): Boolean {
        val faceByteArray = imageLoader.loadImage(faceImg.uri, faceImg is FromFrontCamera)
        val bodyByteArray = imageLoader.loadImage(bodyImg.uri, faceImg is FromFrontCamera)

        val facePath = repository.insertPlayerImage(player.id, faceByteArray, true)
        val bodyPath = repository.insertPlayerImage(player.id, bodyByteArray, false)

        if (facePath.isBlank() || bodyPath.isBlank()) return false

        val updatedPlayer = player.copy(
            id = player.name + "_" + player.id,
            faceImage = facePath,
            bodyImage = bodyPath
        )
        return repository.insertNewPlayer(updatedPlayer)
    }
}
