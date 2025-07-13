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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import com.gbmultiplatform.design_system.style.GBTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GBDialog(
    show: Boolean,
    dismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = show
    ) {
        BasicAlertDialog(
            onDismissRequest = dismiss
        ) {
            content()
        }
    }
}

@Composable
fun GBDialogTitle(
    titleText: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = titleText,
        style = GBTypography().titleMedium.copy(
            fontWeight = Bold
        )
    )
}

@Composable
fun GBDialogTextContent(
    contentText: String
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = contentText,
        style = GBTypography().bodyMedium
    )
}

@Composable
fun GBDialogTextField() {

}
