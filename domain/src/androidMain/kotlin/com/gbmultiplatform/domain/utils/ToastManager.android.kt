package com.gbmultiplatform.domain.utils

import android.content.Context
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import com.gbmultiplatform.domain.utils.IToastManager.ToastDurationType
import com.gbmultiplatform.domain.utils.IToastManager.ToastDurationType.LONG
import com.gbmultiplatform.domain.utils.IToastManager.ToastDurationType.SHORT

class ToastManagerAndroid(
    private val context: Context
): IToastManager {
    override fun showToast(
        msg: String,
        duration: ToastDurationType
    ) {
        val toastDuration = when (duration) {
            SHORT -> LENGTH_SHORT
            LONG -> LENGTH_LONG
        }
        makeText(context, msg, toastDuration).show()
    }
}
