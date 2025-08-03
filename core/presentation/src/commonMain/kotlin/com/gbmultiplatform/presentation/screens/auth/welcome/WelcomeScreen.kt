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

package com.gbmultiplatform.presentation.screens.auth.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBProgressDialog
import com.gbmultiplatform.presentation.navigation.Destination.Stats
import com.gbmultiplatform.presentation.navigation.NavigationState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WelcomeScreen(
    state: NavigationState,
    viewModel: WelcomeViewModel = koinViewModel<WelcomeViewModel>(),
) {
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .padding(bottom = 16.dp)
    ) {
        WelcomeScreenTitle()
        WelcomeScreenImage(Modifier.weight(1f))
        WelcomeScreenButtons {
            state.navigateTo(Stats)
            /* viewModel.onJoinGazteluBira() */
        }
    }

    GBProgressDialog(
        show = isLoading,
        color = White
    )
}
