package com.gbmultiplatform.domain.utils

actual interface PermissionsBridgeListener {
    actual fun requestPermission(permission: PermissionType, callback: PermissionResultCallback)
    actual fun isPermissionsGranted(permission: PermissionType): Boolean
}
