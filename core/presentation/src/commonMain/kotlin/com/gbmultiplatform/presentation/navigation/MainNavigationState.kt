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
import androidx.compose.runtime.State
import com.gbmultiplatform.presentation.screens.welcome.WelcomeScreen

interface MainNavigationState {
    val currentDestination: State<MainDestination?>
    fun navigateBack()
    fun popUpToHome()
    fun navigate(destination: MainDestination)
    fun navigateToTop(destination: MainDestination)
}

interface MainDestination {

    @Composable
    fun Content(state: MainNavigationState)

    object Welcome : MainDestination {
        @Composable
        override fun Content(state: MainNavigationState) {
            WelcomeScreen(mainNavigationState = state)
        }
    }

    object Home : MainDestination {
        @Composable
        override fun Content(state: MainNavigationState) {
//            HomeScreen()
        }
    }
}