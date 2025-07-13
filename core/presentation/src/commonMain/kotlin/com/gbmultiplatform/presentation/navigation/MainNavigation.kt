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
import androidx.compose.runtime.State
import com.gbmultiplatform.presentation.screens.home.HomeScreen
import com.gbmultiplatform.presentation.screens.welcome.WelcomeScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlin.reflect.KClass

interface MainNavigationState {
    val currentDestination: State<MainDestination?>
    fun navigateBack()
    fun popUpToHome()
    fun navigate(destination: MainDestination)
    fun navigateToTop(destination: MainDestination)
}

@Composable
expect fun rememberMainNavigationState(): MainNavigationState

@Composable
expect fun MainNavigation(state: MainNavigationState)

interface MainDestination {

    @Composable
    fun Content(state: MainNavigationState)

    @Serializable
    object Welcome : MainDestination {
        @Composable
        override fun Content(state: MainNavigationState) {
            WelcomeScreen(mainNavigationState = state)
        }
    }

    @Serializable
    object Home : MainDestination {

        @Composable
        override fun Content(state: MainNavigationState) {
            HomeScreen()
        }
    }
}

sealed interface MainDestinationConfiguration<T : MainDestination> {

    val clazz: KClass<T>
    val subclassRegisterer: (PolymorphicModuleBuilder<MainDestination>) -> Unit

    data class NoParams<T : MainDestination>(
        val instance: T,
        override val clazz: KClass<T>,
        override val subclassRegisterer: (PolymorphicModuleBuilder<MainDestination>) -> Unit
    ) : MainDestinationConfiguration<T>

    data class WithArguments<T : MainDestination>(
        override val clazz: KClass<T>,
        override val subclassRegisterer: (PolymorphicModuleBuilder<MainDestination>) -> Unit
    ) : MainDestinationConfiguration<T>

}

inline fun <reified T : MainDestination> T.configuration(): MainDestinationConfiguration.NoParams<T> {
    return MainDestinationConfiguration.NoParams(
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

inline fun <reified T : MainDestination> KClass<T>.configuration(): MainDestinationConfiguration.WithArguments<T> {
    return MainDestinationConfiguration.WithArguments(
        clazz = this,
        subclassRegisterer = {
            it.subclass(
                subclass = this@configuration,
                serializer = kotlinx.serialization.serializer()
            )
        }
    )
}

val defaultMainDestinations: List<MainDestinationConfiguration<*>> = listOf(
    MainDestination.Home.configuration(),
    MainDestination.Welcome.configuration()
)