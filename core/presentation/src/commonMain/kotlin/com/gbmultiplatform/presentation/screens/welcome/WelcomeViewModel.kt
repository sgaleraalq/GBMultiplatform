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

package com.gbmultiplatform.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbmultiplatform.data.db.preferences.UserPreferencesImpl
import com.gbmultiplatform.data.network.auth.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val userPreferences: UserPreferencesImpl
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    fun onJoinGazteluBira(onSuccessfulJoin: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            userPreferences.insertUserId("example.email@gmail.com")
            _isLoading.value = false
        }
    }


    fun onRetrieveUserId() {
        viewModelScope.launch {
            val userEmail = userPreferences.getUserId()
            println("And this is my user email: $userEmail")
        }
    }

}