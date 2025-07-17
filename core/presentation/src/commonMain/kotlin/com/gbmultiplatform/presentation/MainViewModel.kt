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
import com.gbmultiplatform.data.db.preferences.UserPreferencesImpl
import com.gbmultiplatform.presentation.navigation.MainDestination
import com.gbmultiplatform.presentation.navigation.MainDestination.Home
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val userPreferences: UserPreferencesImpl
) : ViewModel() {

    private val screensWithBottomNavigation = listOf(
        Home
    )

    private val _showBottomNav = MutableStateFlow(false)
    val showBottomNav = _showBottomNav


    fun updateBottomNavVisibility(destination: MainDestination?) {
        _showBottomNav.value = screensWithBottomNavigation.contains(destination)
        println("Bottom nav visibility updated: ${_showBottomNav.value} for destination: $destination")
    }
}