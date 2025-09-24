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

import com.gbmultiplatform.domain.model.player.IPlayerProvider
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.PlayerStatsModel
import com.gbmultiplatform.domain.model.player.Position.DEFENDER
import com.gbmultiplatform.domain.model.player.Position.FORWARD
import com.gbmultiplatform.domain.model.player.Position.GOALKEEPER
import com.gbmultiplatform.domain.model.player.Position.MIDFIELDER
import com.gbmultiplatform.domain.utils.generateRandomUUID

class PlayerProvider : IPlayerProvider {

    companion object {
        val RANDOM_IMAGES = listOf(
            "https://static.vecteezy.com/system/resources/thumbnails/054/555/113/small/a-cartoon-character-with-sunglasses-on-his-face-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/059/467/746/small_2x/cool-cartoon-character-with-headphones-and-goggles-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/059/466/443/small/cool-character-wearing-headphones-and-stylish-glasses-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/054/079/216/small_2x/a-cartoon-character-wearing-sunglasses-and-a-cap-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/053/950/452/small/a-male-gamer-with-headphones-on-his-head-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/055/606/226/small_2x/cartoon-boy-pointing-at-something-a-white-background-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/054/297/318/small_2x/a-cartoon-character-with-sunglasses-and-a-tie-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/055/476/176/small/cartoon-muslim-girl-in-hijab-illustration-free-vector.jpg",
            "https://static.vecteezy.com/system/resources/thumbnails/054/077/526/small/a-robot-head-with-red-eyes-and-a-helmet-free-vector.jpg"

        )
        val RANDOM_NAMES = listOf(
            "Mind Craft",
            "Growth Chain",
            "Habit Forge",
            "Spirit Path",
            "Will Power",
            "Inner Arc",
            "Rise Quest",
            "Zen Engine",
            "Focus Fury",
            "Mood Dojo",
            "Grind Shrine",
            "Flow Hack",
            "Dream Stack",
            "Grit Grid",
            "Brain Pump",
            "Soul Gym",
            "Calm Storm",
            "Peak Freak",
            "Aura Sync",
            "Deep Flow",
            "Karma Loop",
            "Push Portal",
            "Focus Cult",
            "Hustle Temple",
            "Vibe Pilot",
            "Goal Ninja",
            "Boost Den",
            "Procrastikill",
            "Flex Mind",
            "Clarity Clash",
            "Balance Beast",
            "Zen Hustle",
            "Gratitude Gang",
            "Power Napper",
            "Chakra Charger",
            "Brain Spa",
            "No Excuse Juice",
            "Flowtopia",
            "Habitron 9000",
            "Inner Beast Mode",
            "Dojo of Discipline",
            "Ultra Calm",
            "Rise & Grindr",
            "Mindset Mafia",
            "The Focus Zone",
            "Energy Bender",
            "Discipline Dungeon",
            "Todo Tornado",
            "Manifesto Club",
            "Life Hacktivist"
        )
    }

    private fun provideStatsForPlayer(): PlayerStatsModel {
        val randomId = generateRandomUUID()
        val randomGoals = (0..10).random()
        val randomAssists = (0..10).random()
        val randomPenaltiesProvoked = (0..5).random()
        val randomCleanSheets = (0..5).random()
        val randomSaves = (0..10).random()
        val randomYellowCards = (0..5).random()
        val randomRedCards = (0..2).random()
        val randomGamesPlayed = (5..30).random()
        val randomPercentage = (0..100).random() / 100.0

        return PlayerStatsModel(
            id = randomId,
            image = RANDOM_IMAGES.random(),
            name = RANDOM_NAMES.random(),
            goals = randomGoals,
            assists = randomAssists,
            penaltiesProvoked = randomPenaltiesProvoked,
            cleanSheets = randomCleanSheets,
            saves = randomSaves,
            yellowCards = randomYellowCards,
            redCards = randomRedCards,
            gamesPlayed = randomGamesPlayed,
            percentage = randomPercentage
        )
    }

    private fun providePlayerInformation(): PlayerInformationModel {
        val position = listOf(GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD).random()

        return PlayerInformationModel(
            id = generateRandomUUID(),
            faceImage = RANDOM_IMAGES.random(),
            bodyImage = RANDOM_IMAGES.random(),
            name = RANDOM_NAMES.random(),
            dorsal = (1..99).random(),
            position = position
        )
    }

    override fun providePlayerList() = List((10..20).random()) {
        provideStatsForPlayer()
    }

    override fun providePlayerInformationList() = List((10..20).random()) {
        providePlayerInformation()
    }

    override fun providePlayerStats() = provideStatsForPlayer()
}