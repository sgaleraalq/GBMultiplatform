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

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder

interface MultiplatformMainNavigationState : MainNavigationState {
    override val currentDestination: State<MainDestination>
    val stateHolder: SaveableStateHolder
}

@Composable
fun MultiplatformMainNavigation(
    state: MainNavigationState,
    initDestination: MainDestination?
) {
    state as MultiplatformMainNavigationState
    MultiplatformBackHandler { state.navigateBack() }
    Crossfade(
        targetState = initDestination
    ) { destination ->
        state.stateHolder.SaveableStateProvider(destination.toString()) {
            key(destination) { destination?.Content(state) }
        }
    }
}

@Composable
fun rememberMultiplatformMainNavigationState(): MainNavigationState {
    val stack = remember { mutableStateOf(listOf<MainDestination>()) }

    val currentDestinationState = remember { derivedStateOf { stack.value.last() } }

    val stateHolder = rememberSaveableStateHolder()

    return remember {
        object : MultiplatformMainNavigationState {

            override val currentDestination = currentDestinationState
            override val stateHolder: SaveableStateHolder = stateHolder

            override fun navigateBack() {
                val currentStack = stack.value
                if (currentStack.size <= 1) return

                val lastItem = currentStack.last()
                stack.value = currentStack.dropLast(1)
                stateHolder.removeState(lastItem.toString())
            }

            override fun popUpToHome() {
                val itemsToRemove = stack.value.drop(1)
                stack.value = stack.value.take(1)
                itemsToRemove.forEach { stateHolder.removeState(it.toString()) }
            }

            override fun navigate(destination: MainDestination) {
                stack.value = stack.value.plus(destination)
            }

            override fun navigateToTop(destination: MainDestination) {
                val currentStack = stack.value
                val destinationIndex = currentStack.indexOfFirst { it::class == destination::class }
                    .takeIf { it != -1 }
                    ?: (currentStack.size - 1)
                stack.value = currentStack.take(destinationIndex).plus(destination)
            }
        }
    }
}