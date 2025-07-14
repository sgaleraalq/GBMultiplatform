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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBDialogTextContent
import com.gbmultiplatform.design_system.components.GBDialogTextField
import com.gbmultiplatform.design_system.components.GBDialogTitle
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.design_system.components.GBText
import com.gbmultiplatform.design_system.components.GBTitle
import com.gbmultiplatform.design_system.style.gb_text_field_background
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.app_name
import gbmultiplatform.core.presentation.generated.resources.create_new_team
import gbmultiplatform.core.presentation.generated.resources.gaztelu_bira_welcome_text
import gbmultiplatform.core.presentation.generated.resources.insert_team_code_to_join
import gbmultiplatform.core.presentation.generated.resources.join
import gbmultiplatform.core.presentation.generated.resources.join_existing_team
import gbmultiplatform.core.presentation.generated.resources.join_team
import gbmultiplatform.core.presentation.generated.resources.welcome_image
import gbmultiplatform.core.presentation.generated.resources.welcome_to
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeScreenTitle() {
    GBTitle(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        title = "${stringResource(Res.string.welcome_to)} ${stringResource(Res.string.app_name)}"
    )
    Spacer(Modifier.height(16.dp))
    GBText(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
        text = stringResource(Res.string.gaztelu_bira_welcome_text),
        alignment = TextAlign.Center
    )
}

@Composable
fun WelcomeScreenImage(
    modifier: Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.welcome_image),
            contentDescription = stringResource(Res.string.app_name),
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun WelcomeScreenButtons(
    navigateToCreateNewTeamScreen: () -> Unit,
    onJoinToExistingTeamClick: () -> Unit
) {
    GBElevatedButton(
        modifier = Modifier.padding(horizontal = 18.dp),
        text = stringResource(Res.string.create_new_team),
        onClick = { navigateToCreateNewTeamScreen() }
    )
    Spacer(Modifier.height(8.dp))
    GBElevatedButton(
        modifier = Modifier.padding(horizontal = 18.dp),
        text = stringResource(Res.string.join_existing_team),
        onClick = { onJoinToExistingTeamClick() },
        backgroundColor = Gray,
        textColor = White
    )
}

@Composable
fun JoinExistingTeamDialogContent(
    groupId: String,
    onGroupIdChange: (String) -> Unit = {},
    onJoinGroup: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(White)
            .padding(16.dp)
    ) {
        GBDialogTitle(
            titleText = stringResource(Res.string.join_team)
        )

        Spacer(Modifier.height(8.dp))
        GBDialogTextContent(
            contentText = stringResource(Res.string.insert_team_code_to_join)
        )
        Spacer(Modifier.height(12.dp))
        GBDialogTextField(
            text = groupId,
            onTextChanged = { onGroupIdChange(it) }
        )
        Spacer(Modifier.height(12.dp))
        GBElevatedButton(
            text = stringResource(Res.string.join),
            onClick = { /* join app */ },
            backgroundColor = gb_text_field_background
        )
    }
}
