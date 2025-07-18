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

package com.gbmultiplatform.data.db.preferences

import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class UserPreferencesImpl(
    private val dataStore: PreferencesDatastore
): ISharedPreferences {
    companion object {
        val USER_EMAIL = stringPreferencesKey("user_email")
    }


    override suspend fun insertUserId(email: String) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                this[USER_EMAIL] = email
            }
        }
    }

    override suspend fun getUserId() =
        dataStore.data.map { it[USER_EMAIL] }.firstOrNull()

    override suspend fun isSessionActive(): Boolean {
        return true
    }
}