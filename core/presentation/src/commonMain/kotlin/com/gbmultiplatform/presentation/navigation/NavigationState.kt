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
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.navigation.compose.rememberNavController
import com.gbmultiplatform.design_system.components.GBBottomNavigationTab
import com.gbmultiplatform.presentation.navigation.Destination.About
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Matches
import com.gbmultiplatform.presentation.navigation.Destination.Stats
import com.gbmultiplatform.presentation.navigation.Destination.Team
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlin.reflect.KClass

interface NavigationState {
    val bottomNavTabs: List<GBBottomNavigationTab>
    val currentDestination: State<Destination?>

    fun navigateTo(destination: Destination)
    fun navigateBack()
}

@Composable
expect fun rememberNavigationState(): NavigationState

@Composable
expect fun MainNavigation(state: NavigationState)

interface MultiplatformNavigationState : NavigationState {
    override val currentDestination: State<Destination>
    val stateHolder: SaveableStateHolder
}

@Composable
fun MultiplatformMainNavigation(
    state: NavigationState
) {
    state as MultiplatformNavigationState

    MultiplatformBackHandler { state.navigateBack() }

    Crossfade(
        targetState = state.currentDestination.value
    ) { destination ->
        state.stateHolder.SaveableStateProvider(destination.toString()) {
            key(destination) { destination.Content(state) }
        }
    }

}

sealed interface DestinationConfiguration<T : Destination> {

    val clazz: KClass<T>
    val subclassRegisterer: (PolymorphicModuleBuilder<Destination>) -> Unit

    data class NoParams<T : Destination>(
        val instance: T,
        override val clazz: KClass<T>,
        override val subclassRegisterer: (PolymorphicModuleBuilder<Destination>) -> Unit
    ) : DestinationConfiguration<T>

    data class WithArguments<T : Destination>(
        override val clazz: KClass<T>,
        override val subclassRegisterer: (PolymorphicModuleBuilder<Destination>) -> Unit
    ) : DestinationConfiguration<T>

}

inline fun <reified T : Destination> T.configuration(): DestinationConfiguration.NoParams<T> {
    return DestinationConfiguration.NoParams(
        instance = this,
        clazz = T::class,
        subclassRegisterer = {
            it.subclass(
                subclass = T::class,
                serializer = kotlinx.serialization.serializer()
            )
        }
    )
}

inline fun <reified T : Destination> KClass<T>.configuration(): DestinationConfiguration.WithArguments<T> {
    return DestinationConfiguration.WithArguments(
        clazz = this,
        subclassRegisterer = {
            it.subclass(
                subclass = this@configuration,
                serializer = kotlinx.serialization.serializer()
            )
        }
    )
}

val defaultDestinations: List<DestinationConfiguration<*>> = listOf(
    Welcome.configuration(),
    Home.configuration(),
    Matches.configuration(),
    Stats.configuration(),
    Team.configuration(),
    About.configuration()
)
