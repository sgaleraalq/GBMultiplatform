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

import com.gbmultiplatform.di.generateRandomUUID
import com.gbmultiplatform.model.team.ITeamProvider
import com.gbmultiplatform.model.team.MatchModel
import com.gbmultiplatform.model.team.TeamModel

class TeamProvider: ITeamProvider {

    companion object {
        const val EXAMPLE_TEAM_1 = "https://static.vecteezy.com/system/resources/previews/011/049/345/non_2x/soccer-football-badge-logo-sport-team-identity-illustrations-isolated-on-white-background-vector.jpg"
        const val EXAMPLE_TEAM_2 = "https://static.vecteezy.com/system/resources/thumbnails/008/280/239/small/football-logo-badge-with-a-soccer-ball-illustration-sport-team-logo-template-vector.jpg"
    }
    override fun provideTeam() = TeamModel(
        id = "1",
        name = "Example Team",
        logo = EXAMPLE_TEAM_1
    )

    override fun provideMatch(): MatchModel {
        return MatchModel(
//            date = now(),
            id = generateRandomUUID(),
            localTeam = provideTeam(),
            visitorTeam = provideTeam(),
            localGoals = 1,
            visitorGoals = 2
        )
    }
}