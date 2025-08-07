package com.gbmultiplatform.presentation.navigation

import kotlinx.cinterop.BetaInteropApi
import platform.Foundation.NSCharacterSet
import platform.Foundation.NSString
import platform.Foundation.URLQueryAllowedCharacterSet
import platform.Foundation.create
import platform.Foundation.stringByAddingPercentEncodingWithAllowedCharacters
import platform.Foundation.stringByRemovingPercentEncoding

@OptIn(BetaInteropApi::class)
actual fun encodeUriComponent(input: String): String {
    val nsString = NSString.create(string = input)
    val allowed = NSCharacterSet.URLQueryAllowedCharacterSet
    val encoded = nsString.stringByAddingPercentEncodingWithAllowedCharacters(allowed)
    return encoded ?: input
}

@OptIn(BetaInteropApi::class)
actual fun decodeUriComponent(input: String): String {
    val nsString = NSString.create(string = input)
    return nsString.stringByRemovingPercentEncoding ?: input
}