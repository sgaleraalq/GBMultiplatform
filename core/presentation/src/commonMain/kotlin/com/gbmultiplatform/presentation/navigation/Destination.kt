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
import com.gbmultiplatform.domain.utils.CameraManagerCompose
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.CommonImage.*
import com.gbmultiplatform.presentation.SplashScreen
import com.gbmultiplatform.presentation.screens.about.AboutScreen
import com.gbmultiplatform.presentation.screens.auth.welcome.WelcomeScreen
import com.gbmultiplatform.presentation.screens.home.HomeScreen
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerScreen
import com.gbmultiplatform.presentation.screens.match_detail.MatchDetailScreen
import com.gbmultiplatform.presentation.screens.matches.MatchesScreen
import com.gbmultiplatform.presentation.screens.review_photo.ReviewImageScreen
import com.gbmultiplatform.presentation.screens.stats.StatsScreen
import com.gbmultiplatform.presentation.screens.team.TeamScreen
import com.gbmultiplatform.presentation.screens.team_detail.PlayerInformationScreen
import kotlinx.serialization.Serializable

interface Destination {
    @Composable
    fun Content(state: NavigationState)
    val routeName: String

    @Serializable
    data object Splash : Destination {
        override val routeName = "splash"

        @Composable
        override fun Content(state: NavigationState) {
            SplashScreen()
        }
    }

    @Serializable
    data object Welcome : Destination {
        override val routeName = "welcome"

        @Composable
        override fun Content(state: NavigationState) {
            WelcomeScreen(state)
        }
    }

    @Serializable
    object Home : Destination {
        override val routeName = "home"

        @Composable
        override fun Content(state: NavigationState) {
            HomeScreen(state)
        }
    }

    @Serializable
    object Matches : Destination {
        override val routeName = "matches"

        @Composable
        override fun Content(state: NavigationState) {
            MatchesScreen(state)
        }
    }

    @Serializable
    object Stats : Destination {
        override val routeName = "stats"

        @Composable
        override fun Content(state: NavigationState) {
            StatsScreen(state = state)
        }
    }

    @Serializable
    object Team : Destination {
        override val routeName = "team"

        @Composable
        override fun Content(state: NavigationState) {
            TeamScreen(state = state)
        }
    }

    @Serializable
    object About : Destination {
        override val routeName = "about"

        @Composable
        override fun Content(state: NavigationState) {
            AboutScreen()
        }
    }

    /**
     * Details screens
     */
    @Serializable
    object MatchDetail : Destination {
        override val routeName = "match_detail"

        @Composable
        override fun Content(state: NavigationState) {
            MatchDetailScreen(state)
        }
    }

    @Serializable
    object PlayerInformation : Destination {
        override val routeName = "player_information"

        @Composable
        override fun Content(state: NavigationState) {
            PlayerInformationScreen()
        }
    }

    /**
     * Insert screens
     */
    @Serializable
    object InsertPlayer : Destination {
        override val routeName = "insert_player"

        @Composable
        override fun Content(state: NavigationState) {
            InsertPlayerScreen(state)
        }
    }

    /**
     * Camera
     */
    @Serializable
    data object Camera : Destination {
        override val routeName = "camera"

        @Composable
        override fun Content(state: NavigationState) {
            CameraManagerCompose(
                navigateToReview = { photoPath ->
                    state.navigateTo(
                        ReviewPhoto(FromCamera(photoPath))
                    )
                },
                navigateBack = { state.navigateBack() }
            )
        }
    }

    @Serializable
    data class ReviewPhoto(
        val image: CommonImage
    ) : Destination {
        override val routeName = "review_photo"

        @Composable
        override fun Content(state: NavigationState) {
            ReviewImageScreen(
                image = image
            )
        }
    }
}
