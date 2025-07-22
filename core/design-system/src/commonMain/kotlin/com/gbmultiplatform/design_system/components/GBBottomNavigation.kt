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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.bottom_nav_bg
import com.gbmultiplatform.design_system.style.bottom_nav_selected

data class GBBottomNavigationTab(
    val destination: String,
    val content: @Composable () -> Unit,
    val onNavigationPressed: () -> Unit
)

@Composable
fun GBBottomNavigation(
    show: Boolean,
    currentDestination: String?,
    states: List<GBBottomNavigationTab>
) {
    if (!show || currentDestination == null) return

    Column(Modifier.shadow(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .wrapContentWidth(align = Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            states.forEach { bottomNav ->
                GBBottomNavItem(
                    isSelected = bottomNav.destination == currentDestination,
                    content = bottomNav.content,
                    navigate = bottomNav.onNavigationPressed
                )
            }
        }
    }
}

// create GBBottomNavItem on bottom nav scope
@Composable
fun RowScope.GBBottomNavItem(
    isSelected: Boolean,
    content: @Composable () -> Unit,
    navigate: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = animateColorAsState(
        if (isSelected) bottom_nav_selected else bottom_nav_bg
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .weight(1f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { navigate() }
            )
            .wrapContentWidth()
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor.value)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = { navigate() }
            )
    ) {
        content()
    }
}
