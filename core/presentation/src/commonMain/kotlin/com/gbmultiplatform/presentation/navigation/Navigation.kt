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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import com.gbmultiplatform.design_system.components.GBBottomNavigation
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_background
import org.jetbrains.compose.resources.painterResource

@Composable
fun Navigation(state: NavigationState) {
    state as MultiplatformNavigationState

    MultiplatformBackHandler { state.navigateBack() }

    val currentDest = state.currentDestination.value
//    val showBottomBar = state.showBottomBar(currentDest.routeName)

    Scaffold(
        bottomBar = {
            if (true /*showBottomBar TODO*/) {
                GBBottomNavigation(
                    states = state.bottomNavTabs,
                    currentDestination = currentDest.routeName
                )
            }
        }
    ) { paddingValues ->
        Image(
            modifier = Modifier.fillMaxSize().padding(
                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
            ),
            painter = painterResource(Res.drawable.img_background),
            contentScale = Crop,
            contentDescription = null
        )

        MainNavigation(state)
    }
}
