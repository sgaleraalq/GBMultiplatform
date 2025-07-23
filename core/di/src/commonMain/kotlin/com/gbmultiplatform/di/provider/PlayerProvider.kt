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

package com.gbmultiplatform.di.provider

import com.gbmultiplatform.model.player.IPlayerProvider
import com.gbmultiplatform.model.player.Player

class PlayerProvider: IPlayerProvider {

    private fun providePlayer(): Player {
        val randomName = "Player 1"
        val randomGoals = (0..10).random()
        return Player(
            id = "",
            name = randomName,
            goals = randomGoals
        )
    }

    override fun providePlayerList() = List((10..20).random()) {
        providePlayer()
    }
}