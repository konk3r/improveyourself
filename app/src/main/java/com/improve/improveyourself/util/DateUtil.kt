package com.improve.improveyourself.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.getTomorrowsDate(): Date {
    val currentTime = time
    val tomorrowsDate = Date(currentTime + DateUtil().MILLISECONDS_IN_DAY)
    return tomorrowsDate.roundDateToDay()
}

fun Date.getYesterdaysDate(): Date {
    val currentTime = time
    val tomorrowsDate = Date(currentTime - DateUtil().MILLISECONDS_IN_DAY)
    return tomorrowsDate.roundDateToDay()
}

fun Date.roundDateToDay() = DateUtil().dateFormat.parse(formatToDay())

fun Date.formatToDay() = DateUtil().dateFormat.format(this)

/**
 * Created by konk3r on 3/9/18.
 */
class DateUtil {
    val MILLISECONDS_IN_SECOND = 1000
    val MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60
    val MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60
    val MILLISECONDS_IN_DAY =  MILLISECONDS_IN_HOUR * 24

    val dateFormat by lazy {
        SimpleDateFormat("yyyyy-MM-dd", Locale.US)
    }
}
