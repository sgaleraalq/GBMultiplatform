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

package com.gbmultiplatform.domain.model.match

import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.cup
import gbmultiplatform.core.presentation.generated.resources.ic_cup
import gbmultiplatform.core.presentation.generated.resources.ic_league
import gbmultiplatform.core.presentation.generated.resources.league
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class MatchType(
    val type: StringResource,
    val icon: DrawableResource
) {
    LEAGUE(Res.string.league, Res.drawable.ic_league),
    CUP(Res.string.cup, Res.drawable.ic_cup)
}