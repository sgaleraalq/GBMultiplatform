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

package com.gbmultiplatform.design_system.model

import com.gbmultiplatform.design_system.model.LineUpType.FOUR_FOUR_TWO
import com.gbmultiplatform.design_system.model.LineUpType.FOUR_THREE_THREE

data class PlayerPosition(
    val role: Role,
    val x: Float,
    val y: Float
)

enum class Role { GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD }

enum class LineUpType(val formation: String) {
    FOUR_THREE_THREE("4-3-3"),
    FOUR_FOUR_TWO("4-4-2");
}

sealed class LineUpFormation(val name: LineUpType, val positions: List<PlayerPosition>) {

    /**
     * 4-3-3 FORMATION
     */
    object FourThreeThree : LineUpFormation(
        FOUR_THREE_THREE,
        listOf(
            PlayerPosition(Role.GOALKEEPER, 0.5f, 0.95f),
            PlayerPosition(Role.DEFENDER, 0.2f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.4f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.6f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.8f, 0.75f),
            PlayerPosition(Role.MIDFIELDER, 0.3f, 0.5f),
            PlayerPosition(Role.MIDFIELDER, 0.5f, 0.5f),
            PlayerPosition(Role.MIDFIELDER, 0.7f, 0.5f),
            PlayerPosition(Role.FORWARD, 0.25f, 0.25f),
            PlayerPosition(Role.FORWARD, 0.5f, 0.2f),
            PlayerPosition(Role.FORWARD, 0.75f, 0.25f),
        )
    )

    /**
     * 4-4-2 FORMATION
     */
    object FourFourTwo : LineUpFormation(
        FOUR_FOUR_TWO,
        listOf(
            PlayerPosition(Role.GOALKEEPER, 0.5f, 0.95f),
            PlayerPosition(Role.DEFENDER, 0.2f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.4f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.6f, 0.75f),
            PlayerPosition(Role.DEFENDER, 0.8f, 0.75f),
            PlayerPosition(Role.MIDFIELDER, 0.2f, 0.5f),
            PlayerPosition(Role.MIDFIELDER, 0.4f, 0.5f),
            PlayerPosition(Role.MIDFIELDER, 0.6f, 0.5f),
            PlayerPosition(Role.MIDFIELDER, 0.8f, 0.5f),
            PlayerPosition(Role.FORWARD, 0.35f, 0.25f),
            PlayerPosition(Role.FORWARD, 0.65f, 0.25f),
        )
    )

    companion object {
        val allFormations = listOf(FourThreeThree, FourFourTwo)
    }
}