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

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
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
import com.gbmultiplatform.presentation.navigation.Destination.Welcome
import org.jetbrains.compose.resources.painterResource


interface NavigationState {
    val bottomNavTabs: List<GBBottomNavigationTab>
    val currentDestination: State<Destination?>
    val stateHolder: SaveableStateHolder

    fun navigateTo(destination: Destination)
    fun navigateBack()
}

@Composable
fun rememberNavigation(): NavigationState {
    val stateHolder = rememberSaveableStateHolder()

    return remember {
        NavigatorHandler(stateHolder)
    }
}

class NavigatorHandler(
    override val stateHolder: SaveableStateHolder
) : NavigationState {
    private val _stack = mutableStateOf<List<Destination>>(listOf(Welcome))
    override val currentDestination: State<Destination?> = derivedStateOf { _stack.value.lastOrNull() }

    override fun navigateTo(destination: Destination) {
        _stack.value = _stack.value + destination
    }

    override fun navigateBack() {
        if (_stack.value.size > 1) {
            val toRemove = _stack.value.last()
            _stack.value = _stack.value.dropLast(1)
            stateHolder.removeState(toRemove.routeName)
        }
    }

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
