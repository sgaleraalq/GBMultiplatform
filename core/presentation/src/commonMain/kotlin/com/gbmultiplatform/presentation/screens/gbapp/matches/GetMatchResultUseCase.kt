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

package com.gbmultiplatform.presentation.screens.gbapp.matches

import com.gbmultiplatform.model.match.MatchModel
import com.gbmultiplatform.model.match.MatchResult
import com.gbmultiplatform.model.match.MatchResult.DEFEAT
import com.gbmultiplatform.model.match.MatchResult.DRAW
import com.gbmultiplatform.model.match.MatchResult.VICTORY
import com.gbmultiplatform.model.team.TeamModel

class GetMatchResultUseCase {
    operator fun invoke(
        match: MatchModel,
        appTeam: TeamModel
    ): MatchResult {
        val isLocal = match.localTeam.id == appTeam.id

        when (isLocal){
            true -> return if (match.localGoals > match.visitorGoals){
                VICTORY
            } else if (match.localGoals < match.visitorGoals) {
                DEFEAT
            } else {
                DRAW
            }
            false -> return if (match.localGoals < match.visitorGoals){
                VICTORY
            } else if (match.localGoals > match.visitorGoals) {
                DEFEAT
            } else {
                DRAW
            }
        }
    }
}