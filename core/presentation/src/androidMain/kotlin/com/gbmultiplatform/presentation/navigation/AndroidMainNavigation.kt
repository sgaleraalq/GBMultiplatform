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

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlin.reflect.KClass

@Composable
actual fun rememberMainNavigationState(): MainNavigationState {
    val navController = rememberNavController()
    return remember { AndroidMainNavigationState(navController, defaultMainDestinations) }
}

@Composable
actual fun MainNavigation(
    state: MainNavigationState
) {
    state as AndroidMainNavigationState

    NavHost(
        navController = state.navHostController,
        startDestination = state.getRoute(state.defaultDestination::class),
        modifier = Modifier.fillMaxSize()
    ) {
        state.applyDestinations(this)
    }
}

@Immutable
private class AndroidMainNavigationState(
    val navHostController: NavHostController,
    val configurations: List<MainDestinationConfiguration<*>>
) : MainNavigationState {

    private val json: Json = getJsonWithSerializableDestinationsSupport()
    private val clazzToConfiguration = configurations.associateBy { it.clazz }
    private val routeToConfiguration = configurations.associateBy { getRoute(it.clazz) }

    private val _currentDestination = mutableStateOf<MainDestination?>(null)
    override val currentDestination: State<MainDestination?> = _currentDestination

    val defaultDestination = MainDestination.Home

    override fun navigateBack() {
        navHostController.navigateUp()
    }

    override fun popUpToHome() {
        navHostController.popBackStack(
            route = getRoute(MainDestination.Home::class),
            inclusive = false
        )
    }

    override fun navigate(destination: MainDestination) {
        val route = getActualRoute(destination)
        navHostController.navigate(route)
    }

    override fun navigateToTop(destination: MainDestination) {
        val destinationRoute = getActualRoute(destination)
        val popUpToRoute = getRoute(destination::class)

        navHostController.navigate(destinationRoute) {
            popUpTo(popUpToRoute) { inclusive = true }
        }
    }

    fun applyDestinations(builder: NavGraphBuilder) {
        configurations.forEach { builder.registerDestination(it) }
    }

    private fun getJsonWithSerializableDestinationsSupport(): Json {
        return Json {
            serializersModule = SerializersModule {
                polymorphic(MainDestination::class) {
                    configurations.forEach { it.subclassRegisterer.invoke(this) }
                }
            }
        }
    }

    private fun NavGraphBuilder.registerDestination(configuration: MainDestinationConfiguration<*>) {
        composable(
            route = getRoute(configuration.clazz),
            arguments = when (configuration) {
                is MainDestinationConfiguration.NoParams -> emptyList()
                is MainDestinationConfiguration.WithArguments -> listOf(
                    serializedDestinationArgument
                )
            },
            content = {
                val destination = getDestination(it)
                _currentDestination.value = destination
                Box(modifier = Modifier.fillMaxSize()) {
                    destination.Content(this@AndroidMainNavigationState)
                }
            }
        )
    }

    fun getRoute(clazz: KClass<out MainDestination>): String {
        val configuration = clazzToConfiguration.getValue(clazz)
        return when (configuration) {
            is MainDestinationConfiguration.NoParams -> clazz.simpleName!!
            is MainDestinationConfiguration.WithArguments -> "${clazz.simpleName}/{$DESTINATION_ARGUMENT_KEY}"
        }
    }

    private fun getActualRoute(destination: MainDestination): String {
        val configuration = clazzToConfiguration.getValue(destination::class)
        if (configuration is MainDestinationConfiguration.NoParams)
            return destination::class.simpleName!!

        val uri = Uri.encode(
            json.encodeToString(PolymorphicSerializer(MainDestination::class), destination)
        )
        return "${destination::class.simpleName}/$uri"
    }

    private fun getDestination(entry: NavBackStackEntry): MainDestination {
        val route = entry.destination.route ?: getRoute(defaultDestination::class)
        val configuration = routeToConfiguration.getValue(route)

        if (configuration is MainDestinationConfiguration.NoParams) {
            return configuration.instance
        }

        val serializedDestination = Uri.decode(
            entry.arguments!!.getString(DESTINATION_ARGUMENT_KEY)!!
        )
        return json.decodeFromString<MainDestination>(serializedDestination)
    }

    companion object {
        private const val DESTINATION_ARGUMENT_KEY = "arg"

        private val serializedDestinationArgument = navArgument(
            name = DESTINATION_ARGUMENT_KEY,
            builder = { type = NavType.StringType }
        )
    }
}
