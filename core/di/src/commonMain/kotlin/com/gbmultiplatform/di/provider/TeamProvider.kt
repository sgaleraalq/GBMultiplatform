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
        const val GAZTELU_IMG = "https://firebasestorage.googleapis.com/v0/b/gbmultiplatform.firebasestorage.app/o/img_gaztelu_bira.webp?alt=media&token=3708f1c5-f9d7-4353-8829-967b21df75ed"
        const val GAZTELU_BIRA = "Gaztelu Bira"
        const val GAZTELU_BIRA_ID = "gaztelu_bira"

        const val EXAMPLE_TEAM_1 = "https://static.vecteezy.com/system/resources/previews/011/049/345/non_2x/soccer-football-badge-logo-sport-team-identity-illustrations-isolated-on-white-background-vector.jpg"
        const val EXAMPLE_TEAM_2 = "https://static.vecteezy.com/system/resources/thumbnails/008/280/239/small/football-logo-badge-with-a-soccer-ball-illustration-sport-team-logo-template-vector.jpg"

        const val TEAM_NAME_1 = "Example Team 1"
        const val TEAM_NAME_2 = "Example Team 2"
    }

    override fun provideMatch(): MatchModel {
        val randomLocalGoals = (0..5).random()
        val randomVisitorGoals = (0..5).random()
        val isLocal = (0..1).random() == 0

        val localTeam = if (isLocal) {
            provideAppTeam()
        } else {
            provideRandomTeam()
        }

        val visitorTeam = if (isLocal) {
            provideRandomTeam()
        } else {
            provideAppTeam()
        }

        return MatchModel(
//            date = now(),
            id = generateRandomUUID(),
            localTeam = localTeam,
            visitorTeam = visitorTeam,
            localGoals = randomLocalGoals,
            visitorGoals = randomVisitorGoals
        )
    }

    override fun provideRandomTeam(): TeamModel {
        val firstExample = (0..1).random() == 0

        return TeamModel(
            id = generateRandomUUID(),
            name = if (firstExample) TEAM_NAME_1 else TEAM_NAME_2,
            logo = if (firstExample) EXAMPLE_TEAM_1 else EXAMPLE_TEAM_2
        )
    }
    override fun provideAppTeam() = TeamModel(
        id = GAZTELU_BIRA_ID,
        name = GAZTELU_BIRA,
        logo = GAZTELU_IMG
    )
}