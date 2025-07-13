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

package com.gbmultiplatform.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.design_system.style.welcome_screen_blue_bg
import gbmultiplatform.core.presentation.generated.resources.Res
import gbmultiplatform.core.presentation.generated.resources.create_new_team
import gbmultiplatform.core.presentation.generated.resources.join_existing_team
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(welcome_screen_blue_bg).padding(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        GBElevatedButton(
            text = stringResource(Res.string.create_new_team),
            onClick = { }
        )
        GBElevatedButton(
            text = stringResource(Res.string.join_existing_team),
            onClick = { }
        )
    }
}