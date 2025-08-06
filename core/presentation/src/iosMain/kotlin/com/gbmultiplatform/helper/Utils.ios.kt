package com.gbmultiplatform.helper

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.systemTimeZone
import platform.Foundation.timeIntervalSince1970

actual fun getActualTimeAsLong() = (NSDate().timeIntervalSince1970 * 1000).toLong()
actual fun getDateFromLong(format: String, date: Long): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
    dateFormatter.timeZone = NSTimeZone.systemTimeZone

    val nsDate = NSDate.dateWithTimeIntervalSince1970(date.toDouble() / 1000)
    return dateFormatter.stringFromDate(nsDate)
}

