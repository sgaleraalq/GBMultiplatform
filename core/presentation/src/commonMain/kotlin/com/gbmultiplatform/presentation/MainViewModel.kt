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
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import com.gbmultiplatform.presentation.navigation.NavigationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

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

    /**
     * Decides whether or not the user has to join the
     * [Welcome] or go [Stats] directly
     */
    suspend fun initApp(
        navController: NavigationState
    ) {
        initializeNavigationState(navController)
        if (sessionActive()) {
            /**
             * INIT NAVIGATION
             */
            navController.navigateTo(
                Home
            )
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

//PlayerInformation(
//"Haaland_f855d86b-b380-478b-a4ad-32169371bbc8"
//)
