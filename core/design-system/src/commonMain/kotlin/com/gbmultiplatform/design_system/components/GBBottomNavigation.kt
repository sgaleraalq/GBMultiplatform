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

package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class GBBottomNavigationState(
    var isSelected: Boolean,
    val icon: DrawableResource,
    val onIconPressed: () -> Unit
)

@Composable
fun GBBottomNavigation(
    show: Boolean,
    states: List<GBBottomNavigationState>
) {
    if (!show) return
    BottomAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        states.forEach { bottomNav ->
            GBBottomNavItem(
                isSelected = bottomNav.isSelected,
                icon = bottomNav.icon,
                navigate = bottomNav.onIconPressed
            )
        }
    }
}

// create GBBottomNavItem on bottom nav scope
@Composable
fun RowScope.GBBottomNavItem(
    isSelected: Boolean,
    icon: DrawableResource,
    navigate: () -> Unit
) {
    NavigationBarItem(
        selected = isSelected,
        onClick = { navigate() },
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null
            )
        },
        alwaysShowLabel = true
    )
}