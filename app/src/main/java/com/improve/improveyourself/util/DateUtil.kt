package com.improve.improveyourself.util

import com.improve.improveyourself.util.DateUtil.Companion.MILLISECONDS_IN_DAY
import java.text.SimpleDateFormat
import java.util.*

fun Date.getTomorrowsDate(): Date {
    val currentTime = time
    val tomorrowsDate = Date(currentTime + DateUtil.MILLISECONDS_IN_DAY)
    return tomorrowsDate.roundDateToDay()
}

fun Date.getYesterdaysDate(): Date {
    val currentTime = time
    val tomorrowsDate = Date(currentTime - DateUtil.MILLISECONDS_IN_DAY)
    return tomorrowsDate.roundDateToDay()
}

fun Date.roundDateToDay() = DateUtil().dateFormat.parse(formatToDay())

fun Date.formatToDay() = DateUtil().dateFormat.format(this)

fun Date.formatToText() = DateUtil().dateTextFormat.format(this)

fun Date.addDay(): Date {
    return Date(time + MILLISECONDS_IN_DAY)
}

fun Date.subtractDay(): Date {
    return Date(time - MILLISECONDS_IN_DAY)
}

fun Date.toYear(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.toMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MONTH)
}

fun Date.toDay(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_WEEK)
}

fun Date.nextInstanceOfTime(hours: Int, minutes: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.HOUR_OF_DAY, hours)
    calendar.set(Calendar.MINUTE, minutes)
    calendar.set(Calendar.SECOND, 0)

    var time = calendar.timeInMillis
    val currentTime = Date().time
    if (time <= currentTime) time += DateUtil.MILLISECONDS_IN_DAY
    return Date(time)
}

/**
 * Created by konk3r on 3/9/18.
 */
class DateUtil {
    companion object {
        val MILLISECONDS_IN_SECOND = 1000
        val MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60
        val MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60
        val MILLISECONDS_IN_DAY =  MILLISECONDS_IN_HOUR * 24
    }

    val dateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    val dateTextFormat by lazy {
        SimpleDateFormat("EEEE, MMMM d", Locale.US)
    }
}
