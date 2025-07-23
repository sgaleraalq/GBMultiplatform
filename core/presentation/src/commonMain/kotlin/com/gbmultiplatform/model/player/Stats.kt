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

package com.gbmultiplatform.model.player

import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.assists
import gbmultiplatform.core.presentation.generated.resources.clean_sheets
import gbmultiplatform.core.presentation.generated.resources.games_played
import gbmultiplatform.core.presentation.generated.resources.goals
import gbmultiplatform.core.presentation.generated.resources.ic_assists
import gbmultiplatform.core.presentation.generated.resources.ic_clean_sheets
import gbmultiplatform.core.presentation.generated.resources.ic_games_played
import gbmultiplatform.core.presentation.generated.resources.ic_goals
import gbmultiplatform.core.presentation.generated.resources.ic_penalties
import gbmultiplatform.core.presentation.generated.resources.ic_red_card
import gbmultiplatform.core.presentation.generated.resources.ic_saves
import gbmultiplatform.core.presentation.generated.resources.ic_yellow_card
import gbmultiplatform.core.presentation.generated.resources.penalties_produced
import gbmultiplatform.core.presentation.generated.resources.red_card
import gbmultiplatform.core.presentation.generated.resources.saves
import gbmultiplatform.core.presentation.generated.resources.yellow_card
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Stats(
    val statName: StringResource,
    val icon: DrawableResource
) {
    GOALS(
        statName = Res.string.goals,
        icon = Res.drawable.ic_goals
    ),
    ASSISTS(
        statName = Res.string.assists,
        icon = Res.drawable.ic_assists
    ),
    PENALTIES_PRODUCED(
        statName = Res.string.penalties_produced,
        icon = Res.drawable.ic_penalties
    ),
    CLEAN_SHEETS(
        statName = Res.string.clean_sheets,
        icon = Res.drawable.ic_clean_sheets
    ),
    SAVES(
        statName = Res.string.saves,
        icon = Res.drawable.ic_saves
    ),
    GAMES_PLAYED(
        statName = Res.string.games_played,
        icon = Res.drawable.ic_games_played
    ),
    YELLOW_CARDS(
        statName = Res.string.yellow_card,
        icon = Res.drawable.ic_yellow_card
    ),
    RED_CARDS(
        statName = Res.string.red_card,
        icon = Res.drawable.ic_red_card
    )
}