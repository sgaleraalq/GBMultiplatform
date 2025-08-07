package com.gbmultiplatform.presentation.navigation

import android.net.Uri
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
import androidx.navigation.compose.NavHost
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
import com.gbmultiplatform.presentation.navigation.Destination.Matches
import com.gbmultiplatform.presentation.navigation.Destination.Stats
import com.gbmultiplatform.presentation.navigation.Destination.Team
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.painterResource
import kotlin.reflect.KClass

@Composable
actual fun rememberNavigationState(): NavigationState {
    val navController = rememberNavController()

    return remember {
        AndroidNavigationState(
            navController,
            defaultDestinations
        )
    }
}

@Composable
actual fun MainNavigation(state: NavigationState) {
    state as AndroidNavigationState

    NavHost(
        navController = state.navHostController,
        startDestination = state.getRoute(state.defaultDestination::class),
        modifier = Modifier.fillMaxSize()
    ) {
        state.applyDestinations(this)
    }
}

class AndroidNavigationState(
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

    val defaultDestination = Home

    override fun navigateBack() {
        navHostController.navigateUp()
    }

    override fun navigateTo(destination: Destination) {
        val route = getActualRoute(destination)
        navHostController.navigate(route)
    }

    fun applyDestinations(builder: NavGraphBuilder) {
        configurations.forEach { builder.registerDestination(it) }
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
                    destination.Content(this@AndroidNavigationState)
                }
            }
        )
    }

    fun getRoute(clazz: KClass<out Destination>): String {
        val configuration = clazzToConfiguration.getValue(clazz)
        return when (configuration) {
            is DestinationConfiguration.NoParams -> clazz.simpleName!!
            is DestinationConfiguration.WithArguments -> "${clazz.simpleName}/{$DESTINATION_ARGUMENT_KEY}"
        }
    }

    private fun getActualRoute(destination: Destination): String {
        val configuration = clazzToConfiguration.getValue(destination::class)
        if (configuration is DestinationConfiguration.NoParams)
            return destination::class.simpleName!!

        val uri = Uri.encode(
            json.encodeToString(PolymorphicSerializer(Destination::class), destination)
        )
        return "${destination::class.simpleName}/$uri"
    }

    private fun getDestination(entry: NavBackStackEntry): Destination {
        val route = entry.destination.route ?: getRoute(defaultDestination::class)
        val configuration = routeToConfiguration.getValue(route)

        if (configuration is DestinationConfiguration.NoParams) {
            return configuration.instance
        }

        val serializedDestination = Uri.decode(
            entry.arguments!!.getString(DESTINATION_ARGUMENT_KEY)!!
        )
        return json.decodeFromString<Destination>(serializedDestination)
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
