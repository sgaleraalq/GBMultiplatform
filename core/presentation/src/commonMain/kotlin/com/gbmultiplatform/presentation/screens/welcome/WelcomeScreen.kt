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

package com.gbmultiplatform.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import com.gbmultiplatform.design_system.components.GBDialog
import com.gbmultiplatform.design_system.components.GBProgressDialog
import com.gbmultiplatform.design_system.style.welcome_screen_blue_bg
import com.gbmultiplatform.presentation.navigation.MainDestination.Home
import com.gbmultiplatform.presentation.navigation.MainNavigationState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WelcomeScreen(
    mainNavigationState: MainNavigationState,
    viewModel: WelcomeViewModel = koinViewModel<WelcomeViewModel>()
) {
    var showJoinToExistingTeamDialog by remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.collectAsState()
    val groupId by viewModel.groupId.collectAsState()

    Column(
        modifier = Modifier
            .safeDrawingPadding()
    ) {
        WelcomeScreenTitle()
        WelcomeScreenImage(Modifier.weight(1f))
        WelcomeScreenButtons(
            navigateToCreateNewTeamScreen = {
                mainNavigationState.navigate(Home)
            }
        )
    }

    GBDialog(
        show = showJoinToExistingTeamDialog,
        dismiss = { showJoinToExistingTeamDialog = false },
        content = {
            JoinExistingTeamDialogContent(
                groupId = groupId,
                onGroupIdChange = {
                    viewModel.onGroupIdChanged(it)
                },
                onJoinGroup = {
                    viewModel.joinTeam(
                        onSuccessfulJoin = {
                            mainNavigationState.navigate(Home)
                        }
                    )
                }
            )
        }
    )

    GBProgressDialog(
        show = isLoading,
        color = White
    )
}
