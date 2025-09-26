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

import com.gbmultiplatform.data.db.preferences.UserPreferencesImpl
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class InitDestinationHandler(
    private val userPreferencesImpl: UserPreferencesImpl? = null
) {
    suspend fun initApp(): Destination {
        delay(2000L)
        return if (sessionActive()) {
            Home
        } else {
            Welcome
        }
    }

    private suspend fun sessionActive(): Boolean {
        return withContext(Dispatchers.IO) {
            userPreferencesImpl?.isSessionActive() ?: false
        }
    }
}