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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gbmultiplatform.design_system.components.GBBottomNavigationTab
import com.gbmultiplatform.design_system.icons.GBAboutBottomTab
import com.gbmultiplatform.design_system.icons.GBHomeBottomTab
import com.gbmultiplatform.design_system.icons.GBIcons
import com.gbmultiplatform.design_system.icons.GBMatchesBottomTab
import com.gbmultiplatform.design_system.icons.GBStatsBottomTab
import com.gbmultiplatform.design_system.icons.GBTeamBottomTab
import com.gbmultiplatform.presentation.navigation.Destination.About
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.MatchDetail
import com.gbmultiplatform.presentation.navigation.Destination.Matches
import com.gbmultiplatform.presentation.navigation.Destination.Stats
import com.gbmultiplatform.presentation.navigation.Destination.Team
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.painterResource
import kotlin.collections.emptyList
import kotlin.reflect.KClass

interface NavigationState {
    val bottomNavTabs: List<GBBottomNavigationTab>
    val currentDestination: State<Destination?>

    fun navigateTo(destination: Destination)
    fun navigateBack()
}

@Composable
fun rememberNavigation(): NavigationState {
    val navController = rememberNavController()

    return remember {
        NavigatorHandler(
            navController,
            defaultDestinations
        )
    }
}

expect fun encodeUriComponent(input: String): String
expect fun decodeUriComponent(input: String): String

class NavigatorHandler(
    val navHostController: NavHostController,
    val configurations: List<DestinationConfiguration<*>>
) : NavigationState {

    companion object {
        private const val DESTINATION_ARGUMENT_KEY = "arg"

        private val serializedDestinationArgument = navArgument(
            name = DESTINATION_ARGUMENT_KEY,
            builder = { type = NavType.StringType }
        )
    }

    private val json: Json = getJsonWithSerializableDestinationsSupport()
    private val clazzToConfiguration = configurations.associateBy { it.clazz }
    private val routeToConfiguration = configurations.associateBy { getRoute(it.clazz) }
    private val _currentDestination = mutableStateOf<Destination?>(null)
    override val currentDestination: State<Destination?> = _currentDestination

    val defaultDestination = Matches

    override fun navigateBack() {
        navHostController.navigateUp()
    }

    override fun navigateTo(destination: Destination) {
        val route = getActualRoute(destination)
        navHostController.navigate(route)
    }

    private fun getActualRoute(destination: Destination): String {
        val configuration = clazzToConfiguration.getValue(destination::class)
        if (configuration is DestinationConfiguration.NoParams)
            return destination::class.simpleName!!

        val uri = encodeUriComponent(
            json.encodeToString(PolymorphicSerializer(Destination::class), destination)
        )
        return "${destination::class.simpleName}/$uri"
    }

    private fun getJsonWithSerializableDestinationsSupport(): Json {
        return Json {
            serializersModule = SerializersModule {
                polymorphic(Destination::class) {
                    configurations.forEach { it.subclassRegisterer.invoke(this) }
                }
            }
        }
    }

    fun getRoute(clazz: KClass<out Destination>): String {
        val configuration = clazzToConfiguration.getValue(clazz)
        return when (configuration) {
            is DestinationConfiguration.NoParams -> clazz.simpleName!!
            is DestinationConfiguration.WithArguments -> "${clazz.simpleName}/{$DESTINATION_ARGUMENT_KEY}"
        }
    }

    fun applyDestinations(builder: NavGraphBuilder) {
        configurations.forEach { builder.registerDestination(it) }
    }

    private fun NavGraphBuilder.registerDestination(configuration: DestinationConfiguration<*>) {
        composable(
            route = getRoute(configuration.clazz),
            arguments = when (configuration) {
                is DestinationConfiguration.NoParams -> emptyList()
                is DestinationConfiguration.WithArguments -> listOf(
                    serializedDestinationArgument
                )
            },
            content = {
                val destination = getDestination(it)
                _currentDestination.value = destination
                Box(modifier = Modifier.fillMaxSize()) {
                    destination.Content(this@NavigatorHandler)
                }
            }
        )
    }

    private fun getDestination(entry: NavBackStackEntry): Destination {
        val route = entry.destination.route ?: getRoute(defaultDestination::class)
        val configuration = routeToConfiguration.getValue(route)

        if (configuration is DestinationConfiguration.NoParams) {
            return configuration.instance
        }

//        val serializedDestination = decodeUriComponent(
//            entry.arguments!!
//                .getString(DESTINATION_ARGUMENT_KEY)!!
//        )
        return json.decodeFromString<Destination>("serializedDestination")
    }

    /**
     * Bottom bar
     */
    override val bottomNavTabs = listOf(
        GBBottomNavigationTab(
            destination = Home.routeName,
            content = { Icon(GBIcons.GBHomeBottomTab, null) },
            onNavigationPressed = { navigateTo(Home) }
        ),
        GBBottomNavigationTab(
            destination = Matches.routeName,
            content = { Icon(painterResource(GBIcons.GBMatchesBottomTab), null) },
            onNavigationPressed = { navigateTo(Matches) }
        ),
        GBBottomNavigationTab(
            destination = Stats.routeName,
            content = { Icon(painterResource(GBIcons.GBStatsBottomTab), null) },
            onNavigationPressed = { navigateTo(Stats) }
        ),
        GBBottomNavigationTab(
            destination = Team.routeName,
            content = { Icon(GBIcons.GBTeamBottomTab, null) },
            onNavigationPressed = { navigateTo(Team) }
        ),
        GBBottomNavigationTab(
            destination = About.routeName,
            content = { Icon(GBIcons.GBAboutBottomTab, null) },
            onNavigationPressed = { navigateTo(About) }
        )
    )
    fun showBottomBar(route: String?): Boolean {
        return route != null && bottomNavTabs.any { it.destination == route }
    }
}

sealed interface DestinationConfiguration<T: Destination> {
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
    About.configuration(),
    MatchDetail.configuration()
)
