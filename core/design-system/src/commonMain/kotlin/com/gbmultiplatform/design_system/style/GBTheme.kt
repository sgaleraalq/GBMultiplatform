package com.gbmultiplatform.design_system.style

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GBColorScheme = ColorScheme(
    primary = primaryColor,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFBB86FC),
    onPrimaryContainer = Color(0xFF3700B3),
    inversePrimary = Color(0xFF03DAC6),
    secondary = Color(0xFF03DAC5),
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color(0xFF000000),
    tertiary = Color(0xFF018786),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF03DAC5),
    onTertiaryContainer = Color(0xFF000000),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF000000),
    surfaceTint = Color(0xFF6200EE),
    inverseSurface = Color(0xFF121212),
    inverseOnSurface = Color(0xFFFFFFFF),
    error = Color(0xFFB00020),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFCF6679),
    onErrorContainer = Color(0xFF3700B3),
    outline = Color(0xFF000000),
    outlineVariant = Color(0xFFBDBDBD),
    scrim = Color(0xFF000000),
    surfaceBright = Color(0xFFFFFFFF),
    surfaceDim = Color(0xFFF5F5F5),
    surfaceContainer = Color(0xFFFFFFFF),
    surfaceContainerHigh = Color(0xFFF5F5F5),
    surfaceContainerHighest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFF5F5F5),
    surfaceContainerLowest = Color(0xFFF5F5F5)
)

@Composable
fun GBTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GBColorScheme,
        typography = gBTypography(),
        content = content
    )
}