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

import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.presentation.navigation.Destination.Camera
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.navigation.navigateForResult

private const val TAKE_PICTURE = "com.gbmultiplatform.presentation.screens.insert_player.TAKE_PICTURE"
suspend fun launchCameraAndWaitForResult(
    state: NavigationState,
    viewModel: InsertPlayerViewModel,
    key: String = TAKE_PICTURE
): CommonImage? {
    return state.navigateForResult<CommonImage>(
        Camera(key),
        key
    ).also { image ->
        viewModel.updatePicture(image)
    }
}
