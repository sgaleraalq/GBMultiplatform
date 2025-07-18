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
import com.gbmultiplatform.presentation.navigation.MainDestination
import com.gbmultiplatform.presentation.navigation.MainDestination.Home
import com.gbmultiplatform.presentation.navigation.MainNavigationState
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel() :
    ViewModel() {

    private val _currentScreenListener = MutableStateFlow<MainDestination?>(null)
    val currentScreen = _currentScreenListener

    fun initListener(navState: MainNavigationState) {
        _currentScreenListener.value = navState.currentDestination.value
    }

    /**
     * Handles bottom navigation on screens
     */

    private val screensWithBottomNavigation = listOf(
        Home
    )
    private val _showBottomNav = MutableStateFlow(false)
    val showBottomNav = _showBottomNav

    fun updateBottomNavVisibility(destination: MainDestination?) {
        _showBottomNav.value = screensWithBottomNavigation.contains(destination)
    }
}