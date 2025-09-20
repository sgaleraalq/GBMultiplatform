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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.gbmultiplatform.presentation.navigation.Destination.Splash

@Composable
fun rememberMultiplatformNavigationState(
    initialNavigationHandler: InitDestinationHandler
): NavigationState {
    var initDestination = remember<Destination> { Splash }

    LaunchedEffect(true) {
        initDestination = initialNavigationHandler.initApp()
    }
    val stack = remember {
        mutableStateOf(
            listOf(initDestination)
        )
    }
    val currentDestinationState = remember { derivedStateOf { stack.value.last() } }

    val stateHolder = rememberSaveableStateHolder()

    return remember {
        object : MultiplatformNavigationState {
            override val currentDestination = currentDestinationState
            override val stateHolder: SaveableStateHolder = stateHolder

            override fun navigateBack() {
                val currentStack = stack.value
                if (currentStack.size <= 1) return

                val lastItem = currentStack.last()
                stack.value = currentStack.dropLast(1)
                stateHolder.removeState(lastItem.toString())
            }

            override fun navigateTo(destination: Destination, clearStack: Boolean) {
                stack.value = stack.value.plus(destination)
                if (clearStack) {
                    val lastItem = stack.value.last()
                    stack.value = listOf(lastItem)
                    stateHolder.removeState { true }
                }
            }

            override fun popUpTo(destination: Destination) {
                val currentStack = stack.value
                val index = currentStack.indexOfLast { it::class == destination::class }
                if (index == -1) return

                val itemsToRemove = currentStack.size - index - 1
                if (itemsToRemove <= 0) return

                val lastItems = currentStack.takeLast(itemsToRemove)
                stack.value = currentStack.dropLast(itemsToRemove)
                lastItems.forEach {
                    stateHolder.removeState(it.toString())
                }
            }
        }
    }
}
