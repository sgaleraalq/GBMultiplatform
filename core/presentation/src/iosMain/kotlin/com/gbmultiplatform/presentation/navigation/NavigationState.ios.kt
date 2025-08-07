package com.gbmultiplatform.presentation.navigation

import androidx.compose.runtime.Composable

@Composable
actual fun rememberNavigationState(): NavigationState =
    rememberMultiplatformNavigationState()

@Composable
actual fun MainNavigation(state: NavigationState) = MultiplatformMainNavigation(state)
