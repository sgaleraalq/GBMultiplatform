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

package com.gbmultiplatform.domain.model.player

import com.gbmultiplatform.domain.model.player.Position.DEFENDER
import com.gbmultiplatform.domain.model.player.Position.FORWARD
import com.gbmultiplatform.domain.model.player.Position.GOALKEEPER
import com.gbmultiplatform.domain.model.player.Position.MIDFIELDER
import com.gbmultiplatform.domain.model.player.Position.UNDEFINED
import gbmultiplatform.domain.generated.resources.Res
import gbmultiplatform.domain.generated.resources.defender
import gbmultiplatform.domain.generated.resources.forward
import gbmultiplatform.domain.generated.resources.goalkeeper
import gbmultiplatform.domain.generated.resources.midfielder
import gbmultiplatform.domain.generated.resources.undefined
import org.jetbrains.compose.resources.StringResource

enum class Position(
    val positionName: StringResource
) {
    UNDEFINED(
        Res.string.undefined
    ),
    GOALKEEPER(
        Res.string.goalkeeper
    ),
    DEFENDER(
        Res.string.defender
    ),
    MIDFIELDER(
        Res.string.midfielder
    ),
    FORWARD(
        Res.string.forward
    )
}

fun mapPosition(position: String): Position = when (position.uppercase()) {
    "GOALKEEPER" -> GOALKEEPER
    "DEFENDER" -> DEFENDER
    "MIDFIELDER" -> MIDFIELDER
    "FORWARD" -> FORWARD
    else -> UNDEFINED
}
