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

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import com.gbmultiplatform.design_system.style.gBTypography

@Composable
fun GBTitle(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = gBTypography().headlineLarge,
    textColor: Color = White
) {
    Text(
        modifier = modifier,
        text = title,
        style = style,
        color = textColor,
        textAlign = Center
    )
}

@Composable
fun GBText(
    modifier: Modifier = Modifier,
    text: String,
    alignment: TextAlign,
    textColor: Color = White,
    style: TextStyle = gBTypography().bodyLarge,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = textColor,
        textAlign = alignment
    )
}
