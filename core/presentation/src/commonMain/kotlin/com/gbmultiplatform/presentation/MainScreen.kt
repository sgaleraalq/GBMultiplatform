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

package com.gbmultiplatform.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import com.gbmultiplatform.design_system.components.GBBottomNavigation
import com.gbmultiplatform.presentation.navigation.MainNavigation
import com.gbmultiplatform.presentation.navigation.rememberNavigationState
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_background
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel<MainViewModel>()
) {
    val navigationState = rememberNavigationState()
    val currentDestinationName = navigationState.currentDestination.value?.routeName

    LaunchedEffect(true) {
        viewModel.initApp(navigationState)
    }

    Scaffold(
        bottomBar = {
            GBBottomNavigation(
                states = navigationState.bottomNavTabs,
                currentDestination = currentDestinationName
            )
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
        MainNavigation(Modifier.padding(paddingValues), navigationState)
    }
}


// TODO
@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        Text(
            "Splash screen",
            color = White
        )
    }
}
