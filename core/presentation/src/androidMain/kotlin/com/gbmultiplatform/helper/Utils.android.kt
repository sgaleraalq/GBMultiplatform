package com.gbmultiplatform.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

actual fun getActualTimeAsLong() = System.currentTimeMillis()

@RequiresApi(Build.VERSION_CODES.O)
actual fun getDateFromLong(format: String, date: Long): String {
    val formatter = DateTimeFormatter.ofPattern(format)
    return Instant.ofEpochMilli(date)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(formatter)
}

