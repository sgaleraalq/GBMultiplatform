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

package com.gbmultiplatform.presentation

import androidx.compose.foundation.Image
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.gbmultiplatform.design_system.components.GBBottomNavigation
import com.gbmultiplatform.presentation.navigation.Destination
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import com.gbmultiplatform.presentation.navigation.Navigation
import com.gbmultiplatform.presentation.screens.auth.welcome.WelcomeScreen
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_background
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel<MainViewModel>()
) {
//    val showBottomNavigation by viewModel.showBottomNav.collectAsState()
//    val initScreen by viewModel.initScreen.collectAsState()

    val navController = rememberNavController()
    val navState = rememberNavState()

    Scaffold(
        bottomBar = {
            GBBottomNavigation(
                show = false,
                states = emptyList()
            )
        }
    ){
        Image(
            painter = painterResource(Res.drawable.img_background),
            contentDescription = null
        )
        Navigation(
            navController = navController,
            destinations = listOf(
                Welcome, Home
            )
        )
    }
}
