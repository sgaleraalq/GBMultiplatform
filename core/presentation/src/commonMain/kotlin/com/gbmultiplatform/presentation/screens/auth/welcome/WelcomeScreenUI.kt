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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.presentation.navigation.Destination
import com.gbmultiplatform.presentation.navigation.Destination.Home
import com.gbmultiplatform.presentation.navigation.Destination.Login
import com.gbmultiplatform.presentation.navigation.Destination.SignUp
import com.gbmultiplatform.presentation.navigation.NavigationState
import com.gbmultiplatform.presentation.screens.auth.welcome.ui.WelcomeScreenButtons
import com.gbmultiplatform.presentation.screens.auth.welcome.ui.WelcomeScreenImage
import com.gbmultiplatform.presentation.screens.auth.welcome.ui.WelcomeScreenSubtitle
import com.gbmultiplatform.presentation.screens.auth.welcome.ui.WelcomeScreenTitle

@Composable
fun WelcomeScreenUI(
    state: NavigationState,
    navigateTo: (Destination) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(bottom = 16.dp)
    ) {
        WelcomeScreenTitle()
        Spacer(Modifier.height(16.dp))
        WelcomeScreenSubtitle()
        WelcomeScreenImage(Modifier.height(400.dp))
        WelcomeScreenButtons(
            navigateToSignUp = { navigateTo(SignUp) },
            navigateToLogin = { navigateTo(Login) },
            navigateToCreateNewTeamScreen = {
                state.navigateTo(Home)
                /* viewModel.onJoinGazteluBira() */
            }
        )
    }
}
