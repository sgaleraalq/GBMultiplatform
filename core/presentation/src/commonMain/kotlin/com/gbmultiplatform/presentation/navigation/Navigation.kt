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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.gbmultiplatform.design_system.components.GBBottomNavigation
import com.gbmultiplatform.presentation.navigation.Destination.About
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Matches
import com.gbmultiplatform.presentation.navigation.Destination.Stats
import com.gbmultiplatform.presentation.navigation.Destination.Team
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_background
import org.jetbrains.compose.resources.painterResource

@Composable
fun Navigation(
    state: NavigationState
) {
    state as NavigatorHandler

    MultiplatformBackHandler { state.navigateBack() }

    val initDestination = state.currentDestination.value

    val currentBackStackEntry by state.navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBottomBar = state.showBottomBar(currentRoute)

    println("Bottombaaar = $showBottomBar")

    if (initDestination != null) {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    GBBottomNavigation(
                        states = state.bottomNavTabs,
                        currentDestination = currentRoute
                    )
                }
            }
        ) { paddingValues ->
            Image(
                modifier = Modifier.fillMaxSize().padding(
                    bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                ),
                painter = painterResource(Res.drawable.img_background),
                contentScale = Crop,
                contentDescription = null
            )
            NavHost(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                navController = state.navController,
                startDestination = initDestination
            ) {
                composable<Welcome> {
                    Welcome.Content(state)
                }

                composable<Stats> {
                    Stats.Content(state)
                }

//                navigation(
//                    startDestination = Stats.fullRoute,
//                    route = GBApp.parentRoute
//                ) {
//                    composable<Home> {
//                        Home.Content(state)
//                    }
//
//                    composable<Team> {
//                        Team.Content(state)
//                    }
//
//                    composable<Stats> {
//                        Stats.Content(state)
//                    }
//
//                    composable<Matches> {
//                        Matches.Content(state)
//                    }
//
//                    composable<About> {
//                        About.Content(state)
//                    }
//                }
            }
        }
    }
}
