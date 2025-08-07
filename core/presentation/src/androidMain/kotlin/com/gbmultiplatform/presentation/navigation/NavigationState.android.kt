package com.gbmultiplatform.presentation.navigation

import android.net.Uri

actual fun encodeUriComponent(input: String): String {
    return Uri.encode(input)
}

actual fun decodeUriComponent(input: String): String {
    return Uri.decode(input)
}