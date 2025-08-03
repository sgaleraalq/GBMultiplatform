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

package com.gbmultiplatform.presentation.navigation

import androidx.compose.runtime.Composable
import com.gbmultiplatform.presentation.screens.auth.welcome.WelcomeScreen
import com.gbmultiplatform.presentation.screens.gbapp.about.AboutScreen
import com.gbmultiplatform.presentation.screens.gbapp.home.HomeScreen
import com.gbmultiplatform.presentation.screens.gbapp.matches.MatchesScreen
import com.gbmultiplatform.presentation.screens.gbapp.stats.StatsScreen
import com.gbmultiplatform.presentation.screens.gbapp.team.TeamScreen
import kotlinx.serialization.Serializable

interface Destination {
    @Composable
    fun Content(state: NavigationState)

    val routeName: String

    @Serializable
    data object Welcome: Destination {
        override val routeName = "welcome"

        @Composable
        override fun Content(state: NavigationState) {
            WelcomeScreen(state)
        }
    }

    @Serializable
    object Home: Destination {
        override val routeName = "home"

        @Composable
        override fun Content(state: NavigationState) {
            HomeScreen(state)
        }
    }

    @Serializable
    object Matches: Destination {
        override val routeName = "matches"

        @Composable
        override fun Content(state: NavigationState) {
            MatchesScreen()
        }
    }

    @Serializable
    object Stats: Destination {
        override val routeName = "stats"

        @Composable
        override fun Content(state: NavigationState) {
            StatsScreen(state = state)
        }
    }

    @Serializable
    object Team: Destination {
        override val routeName = "team"

        @Composable
        override fun Content(state: NavigationState) {
            TeamScreen()
        }
    }

    @Serializable
    object About: Destination {
        override val routeName = "about"

        @Composable
        override fun Content(state: NavigationState) {
            AboutScreen()
        }
    }
}