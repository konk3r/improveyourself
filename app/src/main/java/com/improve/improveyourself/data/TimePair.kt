package com.improve.improveyourself.data

/**
 * Created by konk3r on 3/11/18.
 */
data class TimePair(val hour: Int, val minutes: Int) {

    fun format(): String {
        var hour = if (this.hour == 0) 12 else this.hour
        val minutes = minutes
        val isPm = hour >= 12
        if (hour > 12) {
            hour -= 12
        }

        val amOrPm = if (isPm) "PM" else "AM"
        val minutesString = if (minutes < 10) "0$minutes" else minutes.toString()
        return "$hour:$minutesString $amOrPm"
    }
}
