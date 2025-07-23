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

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gbmultiplatform.presentation.navigation.Destination.*

@Composable
fun Navigation(
    state: NavigationState
) {
    state as NavigatorHandler

    MultiplatformBackHandler { state.navigateBack() }

    val current = state.currentDestination.value

    if (current != null) {
        NavHost(
            modifier = Modifier.padding(
                top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding(),
            ),
            navController = state.navController,
            startDestination = current,
        ) {
            composable<Welcome> {
                Welcome.Content(state)
            }

            composable<Home> {
                Home.Content(state)
            }

            composable<Team> {
                Team.Content(state)
            }

            composable<Stats> {
                Stats.Content(state)
            }

            composable<Matches> {
                Matches.Content(state)
            }

            composable<About> {
                About.Content(state)
            }
        }
    }
}
