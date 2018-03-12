package com.improve.improveyourself.ui.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.support.v4.app.AlarmManagerCompat
import com.improve.improveyourself.data.PreferenceManager
import com.improve.improveyourself.data.TimePair
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver.values.CHECK_IN_ID
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver.values.SET_GOAL_ID
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.util.nextInstanceOfTime
import java.util.*


/**
 * Created by konk3r on 3/10/18.
 */
class NotificationAlarmManager( val app: ImproveApp,
                                val alarmManager: AlarmManager,
                                val notificationManager: NotificationManager,
                                val preferenceManager: PreferenceManager) {

    fun setup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val notificationChannel = NotificationChannel(GOAL_CHANNEL_ID, "Goals", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    fun setCheckInTime(hours: Int, minutes: Int) {
        preferenceManager.enableCheckInNotifications(TimePair(hours, minutes))
        scheduleNextCheckInAlarm()
    }

    fun scheduleNextCheckInAlarm() {
        val timePair = preferenceManager.getCheckInNotificationsTime()
        val eventTime = Date().nextInstanceOfTime(timePair.hour, timePair.minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createCheckInNotificationIntent(app))
    }

    fun setSetGoalsTime(hours: Int, minutes: Int) {
        preferenceManager.enableSetGoalsNotifications(TimePair(hours, minutes))
        scheduleNextSetGoalsAlarm()
    }

    private fun scheduleNextSetGoalsAlarm() {
        val timePair = preferenceManager.getSetGoalsNotificationsTime()
        val eventTime = Date().nextInstanceOfTime(timePair.hour, timePair.minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createSetGoalNotificationIntent(app))
    }

    private fun createCheckInNotificationIntent(app: ImproveApp): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, CHECK_IN_ID)
        return PendingIntent.getBroadcast(this.app, CHECK_IN_ID, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetGoalNotificationIntent(app: ImproveApp): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, SET_GOAL_ID)
        return PendingIntent.getBroadcast(this.app, SET_GOAL_ID, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object IDs {
        val GOAL_CHANNEL_ID = "goal_id"
    }

}