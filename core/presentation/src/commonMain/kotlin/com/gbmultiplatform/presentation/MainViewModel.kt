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

class MainViewModel(
    private val userPreferencesImpl: UserPreferencesImpl
) : ViewModel() {


    /**
     * Handles bottom navigation on screens
     */

//    private val screensWithBottomNavigation = listOf(
//        Home
//    )
//    private val _showBottomNav = MutableStateFlow(false)
//    val showBottomNav = _showBottomNav
//
//    fun updateBottomNavVisibility(destination: MainDestination?) {
//        _showBottomNav.value = screensWithBottomNavigation.contains(destination)
//    }

    /**
     * Decides whether or not the user has to join the
     * [Welcome] or go [Home] directly
     */
//    suspend fun initApp() {
//        if (sessionActive()) {
//            _initScreen.value = Home
//        } else {
//            _initScreen.value = Welcome
//        }
//    }
//
//    private suspend fun sessionActive(): Boolean {
//        return withContext(Dispatchers.IO) {
//            userPreferencesImpl.isSessionActive()
//        }
//    }
}