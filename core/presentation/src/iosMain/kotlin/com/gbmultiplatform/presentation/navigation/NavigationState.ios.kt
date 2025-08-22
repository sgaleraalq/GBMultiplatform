package com.gbmultiplatform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun rememberNavigationState(): NavigationState =
    rememberMultiplatformNavigationState(
        InitDestinationHandler()
    )

@Composable
actual fun MainNavigation(modifier: Modifier, state: NavigationState) =
    MultiplatformMainNavigation(modifier, state)
