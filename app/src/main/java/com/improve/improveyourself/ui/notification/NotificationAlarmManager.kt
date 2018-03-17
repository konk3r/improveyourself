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
import com.improve.improveyourself.data.model.NotificationStatus
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver.values.CHECK_IN_ID
import com.improve.improveyourself.receivers.NotificationBroadcastReceiver.values.SET_GOAL_ID
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.util.nextInstanceOfTime
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import java.util.*

/**
 * Created by konk3r on 3/10/18.
 */
class NotificationAlarmManager( val app: ImproveApp,
                                val alarmManager: AlarmManager,
                                val notificationManager: NotificationManager,
                                val preferenceManager: PreferenceManager) {

    val checkInStatusObservable: BehaviorRelay<NotificationStatus> = BehaviorRelay.create()
    val setGoalsStatusObservable: BehaviorRelay<NotificationStatus> = BehaviorRelay.create()

    fun setup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val notificationChannel = NotificationChannel(GOAL_CHANNEL_ID, "Goals", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
        setupObservables()
    }

    private fun setupObservables() {
        val checkInTime = preferenceManager.getCheckInNotificationsTime()
        val setGoalsTime = preferenceManager.getSetGoalsNotificationsTime()
        val checkInNotificationsAreEnabled = preferenceManager.checkInNotificationsAreEnabled()
        val setGoalsNotificationsAreEnabled = preferenceManager.setGoalsNotificationsAreEnabled()

        checkInStatusObservable.accept(NotificationStatus(checkInNotificationsAreEnabled, checkInTime))
        setGoalsStatusObservable.accept(NotificationStatus(setGoalsNotificationsAreEnabled, setGoalsTime))
    }

    fun setCheckInTime(hours: Int, minutes: Int) {
        val time = TimePair(hours, minutes)
        preferenceManager.enableCheckInNotifications(time)
        checkInStatusObservable.accept(NotificationStatus(true, time))
        scheduleNextCheckInAlarm()
    }

    fun scheduleNextCheckInAlarm() {
        val timePair = preferenceManager.getCheckInNotificationsTime()
        val eventTime = Date().nextInstanceOfTime(timePair.hour, timePair.minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createCheckInNotificationIntent())
    }

    fun setSetGoalsTime(hours: Int, minutes: Int) {
        val time = TimePair(hours, minutes)
        preferenceManager.enableSetGoalsNotifications(time)
        setGoalsStatusObservable.accept(NotificationStatus(true, time))
        scheduleNextSetGoalsAlarm()
    }

    fun scheduleNextSetGoalsAlarm() {
        val timePair = preferenceManager.getSetGoalsNotificationsTime()
        val eventTime = Date().nextInstanceOfTime(timePair.hour, timePair.minutes)
        AlarmManagerCompat.setExact(alarmManager,
                AlarmManager.RTC_WAKEUP,
                eventTime.time,
                createSetGoalNotificationIntent())
    }

    private fun createCheckInNotificationIntent(): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, CHECK_IN_ID)
        alarmIntent.action = NotificationBroadcastReceiver.ACTION_SEND_NOTIFICATION
        return PendingIntent.getBroadcast(this.app, CHECK_IN_ID, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetGoalNotificationIntent(): PendingIntent {
        val alarmIntent = Intent(this.app, NotificationBroadcastReceiver::class.java)
        alarmIntent.putExtra(NotificationBroadcastReceiver.TYPE, SET_GOAL_ID)
        alarmIntent.action = NotificationBroadcastReceiver.ACTION_SEND_NOTIFICATION
        return PendingIntent.getBroadcast(this.app, SET_GOAL_ID, alarmIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object IDs {
        val GOAL_CHANNEL_ID = "goal_id"
    }

    fun getCheckInObservable(): Relay<NotificationStatus> {
        return checkInStatusObservable
    }

    fun getSetGoalsObservable(): Relay<NotificationStatus> {
        return setGoalsStatusObservable
    }

    fun disableCheckInNotifications() {
        preferenceManager.disableCheckInNotifications()
        val checkInTime = preferenceManager.getCheckInNotificationsTime()
        checkInStatusObservable.accept(NotificationStatus(false, checkInTime))
    }

    fun disableSetGoalsNotifications() {
        val setGoalsTime = preferenceManager.getSetGoalsNotificationsTime()
        preferenceManager.disableSetGoalsNotifications()
        setGoalsStatusObservable.accept(NotificationStatus(false, setGoalsTime))
    }

    fun getCheckInTime(): TimePair {
        return preferenceManager.getCheckInNotificationsTime()
    }

    fun getSetGoalsTime(): TimePair {
        return preferenceManager.getSetGoalsNotificationsTime()
    }

}