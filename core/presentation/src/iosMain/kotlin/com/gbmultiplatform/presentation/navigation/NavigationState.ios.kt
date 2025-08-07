package com.gbmultiplatform.presentation.navigation

import androidx.compose.runtime.Composable
import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.Foundation.stringByRemovingPercentEncoding

@Composable
actual fun rememberNavigationState(): NavigationState =
    rememberMultiplatformNavigationState()

@Composable
actual fun MainNavigation(state: NavigationState) = MultiplatformMainNavigation(state)
