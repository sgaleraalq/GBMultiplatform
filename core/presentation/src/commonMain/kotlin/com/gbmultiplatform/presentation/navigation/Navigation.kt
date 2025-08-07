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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.gbmultiplatform.design_system.components.GBBottomNavigationTab
import com.gbmultiplatform.presentation.navigation.Destination.Home

@Composable
fun rememberMultiplatformNavigationState(): NavigationState {
    val stack = remember { mutableStateOf<List<Destination>>(listOf(Home)) }
    val currentDestinationState = remember { derivedStateOf { stack.value.last() } }

    val stateHolder = rememberSaveableStateHolder()

    return remember {
        object : MultiplatformNavigationState {
            override val bottomNavTabs: List<GBBottomNavigationTab>
                get() = emptyList()

            override val currentDestination = currentDestinationState
            override val stateHolder: SaveableStateHolder = stateHolder

            override fun navigateBack() {
                val currentStack = stack.value
                if (currentStack.size <= 1) return

                val lastItem = currentStack.last()
                stack.value = currentStack.dropLast(1)
                stateHolder.removeState(lastItem.toString())
            }

            override fun navigateTo(destination: Destination) {
                stack.value = stack.value.plus(destination)
            }
        }
    }
}
