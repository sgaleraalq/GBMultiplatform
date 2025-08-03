package com.gbmultiplatform.di

import platform.Foundation.NSUUID

actual fun generateRandomUUID() = NSUUID().UUIDString()