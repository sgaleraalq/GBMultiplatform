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

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.gbmultiplatform.data.db.preferences.UserPreferencesImpl
import com.gbmultiplatform.design_system.components.GBBottomNavigationState
import com.gbmultiplatform.presentation.navigation.Destination
import com.gbmultiplatform.presentation.navigation.Destination.*
import com.gbmultiplatform.presentation.navigation.NavigationState
import gbmultiplatform.core.presentation.generated.resources.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource

class MainViewModel(
    private val userPreferencesImpl: UserPreferencesImpl
) : ViewModel() {

    /**
     * Handles bottom navigation on screens
     */
    private lateinit var navState: NavigationState

    private fun initializeNavigationState(state: NavigationState) {
        navState = state
    }

    private val screensWithBottomNavigation: List<Destination> = listOf(
        Home, Matches, Stats
    )
    private val _showBottomNav = MutableStateFlow(false)
    val showBottomNav = _showBottomNav

    private val _bottomNavState = MutableStateFlow(
        screensWithBottomNavigation.map { bottomDestination ->
            GBBottomNavigationState(
                isSelected = false,
                icon = null,
                onNavigationPressed = { navState.navigateTo(bottomDestination) }
            )
        }
    )
    val bottomNavState = _bottomNavState

    fun updateBottomNavVisibility(destination: Destination?) {
        _showBottomNav.value = screensWithBottomNavigation.contains(destination)
    }

    /**
     * Decides whether or not the user has to join the
     * [Welcome] or go [Home] directly
     */
    suspend fun initApp(navController: NavigationState) {
        initializeNavigationState(navController)
        if (sessionActive()) {
            navController.navigateTo(Home)
        } else {
            navController.navigateTo(Welcome)
        }
    }

    private suspend fun sessionActive(): Boolean {
        return withContext(Dispatchers.IO) {
            userPreferencesImpl.isSessionActive()
        }
    }
}
