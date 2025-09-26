package com.gbmultiplatform.presentation.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gbmultiplatform.presentation.navigation.NavigationState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Composable
actual fun rememberHomeNavigationState(tab: HomeTab): HomeNavigationState {
    val navController = rememberNavController()
    val tabState = rememberSaveable { mutableStateOf(tab) }

    /**
     * Caching current tab value because back stack becomes null for a moment after config change
     * and wrong selected tab is displayed
     */
    LaunchedEffect(Unit) {
        navController.currentBackStackEntryFlow
            .map { it.destination.route?.let { route -> HomeTab.valueOf(route) } }
            .filterNotNull()
            .onEach { tabState.value = it }
            .collect()
    }

    return remember { AndroidHomeNavigationState(tabState, navController, tab) }
}

private val HomeTab.route: String get() = this.name

class AndroidHomeNavigationState(
    tabState: State<HomeTab>,
    val navHost: NavHostController,
    val defaultTab: HomeTab
) : HomeNavigationState {

    override val selectedTab: State<HomeTab> = tabState

    override fun navigate(tab: HomeTab) {
        navHost.navigate(tab.route) {
            popUpTo(navHost.currentDestination!!.route!!) {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
actual fun HomeNavigationContent(
    homeState: HomeNavigationState,
    mainState: NavigationState
) {
    homeState as AndroidHomeNavigationState

    NavHost(
        navController = homeState.navHost,
        startDestination = homeState.defaultTab.route,
        modifier = Modifier.fillMaxSize()
    ) {
        HomeTab.entries.forEach { tab ->
            composable(
                route = tab.route,
                content = { tab.content(mainState) }
            )
        }
    }
}
