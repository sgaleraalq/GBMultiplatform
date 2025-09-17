package com.gbmultiplatform

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class CameraActivity: AppCompatActivity() {

    companion object {
        fun startCamera(context: Context) {
            val intent = Intent(context, CameraActivity::class.java)
        }
    }
}