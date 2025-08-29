package com.gbmultiplatform.domain.utils

import com.gbmultiplatform.domain.utils.IToastManager.ToastDurationType.SHORT

interface IToastManager {
    enum class ToastDurationType {
        SHORT, LONG
    }

    fun showToast(msg: String, duration: ToastDurationType = SHORT)
}