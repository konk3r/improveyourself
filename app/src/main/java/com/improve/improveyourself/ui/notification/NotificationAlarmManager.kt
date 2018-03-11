package com.improve.improveyourself.ui.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.support.v4.app.AlarmManagerCompat
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
                                val notificationManager: NotificationManager) {

    fun setup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val notificationChannel = NotificationChannel(GOAL_CHANNEL_ID, "Goals", IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    fun setCheckInTime(hours: Int, minutes: Int) {
        val eventTime = Date().nextInstanceOfTime(hours, minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createCheckInNotificationIntent(app))
    }

    fun setSetGoalsTime(hours: Int, minutes: Int) {
        val eventTime = Date().nextInstanceOfTime(hours, minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createSetGoalNotificationIntent(app))
    }

    private fun createCheckInNotificationIntent(app: ImproveApp): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, CHECK_IN_ID)
        return PendingIntent.getBroadcast(this.app, 0, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetGoalNotificationIntent(app: ImproveApp): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, SET_GOAL_ID)
        return PendingIntent.getBroadcast(this.app, 0, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }


    companion object IDs {
        val GOAL_CHANNEL_ID = "goal_id"
    }

}