package com.gbmultiplatform.domain.utils

import platform.Foundation.NSUUID

actual fun generateRandomUUID() = NSUUID().UUIDString()