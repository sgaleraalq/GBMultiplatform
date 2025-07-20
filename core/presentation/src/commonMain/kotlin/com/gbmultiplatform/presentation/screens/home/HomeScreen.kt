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

package com.gbmultiplatform.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import com.gbmultiplatform.design_system.components.GBElevatedButton
import com.gbmultiplatform.presentation.navigation.NavigationState

@Composable
fun HomeScreen(
    state: NavigationState
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Red),
        contentAlignment = Center
    ) {
        GBElevatedButton(
            text = "Go back",
            onClick = { state.navigateBack() }
        )
    }

}
