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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.gbmultiplatform.design_system.components.GBBottomNavigation
import com.gbmultiplatform.presentation.navigation.MainNavigation
import com.gbmultiplatform.presentation.navigation.rememberMainNavigationState
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.img_background
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel<MainViewModel>()
) {
    val state = rememberMainNavigationState()

    val initDestination by viewModel.currentScreen.collectAsState()
    val showBottomNavigation by viewModel.showBottomNav.collectAsState()

    LaunchedEffect(true) {
        viewModel.initListener(state)
    }

    Scaffold(
        bottomBar = {
            GBBottomNavigation(
                show = showBottomNavigation,
                states = emptyList()
            )
        }
    ){
        Image(
            painter = painterResource(Res.drawable.img_background),
            contentDescription = null
        )
        MainNavigation(state, initDestination)
    }
}
