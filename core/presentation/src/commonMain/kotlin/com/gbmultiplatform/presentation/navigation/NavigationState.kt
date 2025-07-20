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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

interface NavigationState {
    val currentDestination: State<Destination?>
    fun navigateTo(destination: Destination)
    fun navigateBack()
}

@Composable
fun rememberNavigation(): NavigationState {
    val navController = rememberNavController()
    return remember { NavigatorHandler(navController) }
}

class NavigatorHandler(
    val navController: NavHostController
): NavigationState {
    private val stack = mutableListOf<Destination>()
    override val currentDestination: State<Destination?> = mutableStateOf(null)

    override fun navigateTo(destination: Destination) {
        stack.add(destination)
        (currentDestination as MutableState).value = destination
    }

    override fun navigateBack() {
        if (stack.size > 1) {
            stack.removeLast()
            (currentDestination as MutableState).value = stack.last()
        }
    }

    fun getCurrentDestination() = currentDestination.value
}
