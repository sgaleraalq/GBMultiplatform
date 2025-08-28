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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction.Companion.Next
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gb_text_field_background
import com.gbmultiplatform.design_system.style.gb_text_field_label_color

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
        style = gBTypography().titleMedium.copy(
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
        style = gBTypography().bodyMedium
    )
}

@Composable
fun GBTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    label: String = "",
    enabled: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { onTextChanged(it) },
        enabled = enabled,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = White,
            focusedContainerColor = gb_text_field_background,
            unfocusedIndicatorColor = Black
        ),
        label = {
            GBText(text = label, textColor = gb_text_field_label_color.copy(alpha = 0.5f),
                style = gBTypography().bodyMedium.copy(fontStyle = Italic))
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = Next),
        keyboardActions = KeyboardActions(
            onNext = {
                keyboardController?.hide()
            }
        )
    )
}
