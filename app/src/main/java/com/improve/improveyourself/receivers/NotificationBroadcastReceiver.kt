package com.improve.improveyourself.receivers

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.improve.improveyourself.R
import com.improve.improveyourself.data.PreferenceManager
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.notification.NotificationAlarmManager
import com.improve.improveyourself.ui.notification.NotificationAlarmManager.IDs.GOAL_CHANNEL_ID
import javax.inject.Inject

/**
 * Created by konk3r on 3/10/18.
 */
class NotificationBroadcastReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationManager: NotificationManagerCompat
    @Inject lateinit var preferenceManager: PreferenceManager
    @Inject lateinit var notificationAlarmManager: NotificationAlarmManager
    lateinit var app: ImproveApp

    override fun onReceive(context: Context?, intent: Intent?) {
        app = context?.applicationContext as ImproveApp
        app.component.inject(this)

        if (intent?.action == null) {
            return
        }

        when (intent.action) {
            ACTION_BOOT_COMPLETED -> restoreAlarms()
            ACTION_SEND_NOTIFICATION -> sendNotification(intent.getIntExtra(TYPE, NO_ID_SET))
        }
    }

    private fun restoreAlarms() {
        if (preferenceManager.checkInNotificationsAreEnabled()) {
            notificationAlarmManager.scheduleNextCheckInAlarm()
        }

        if (preferenceManager.setGoalsNotificationsAreEnabled()) {
            notificationAlarmManager.scheduleNextSetGoalsAlarm()
        }
    }

    private fun sendNotification(type: Int) {
        when (type) {
            CHECK_IN_ID -> sendCheckInNotification()
            SET_GOAL_ID -> sendSetGoalNotification()
        }
    }

    private fun sendCheckInNotification() {
        if (!preferenceManager.checkInNotificationsAreEnabled()) {
            return
        }

        val notification = buildCheckInNotification()
        notificationManager.notify(CHECK_IN_ID, notification)
        notificationAlarmManager.scheduleNextCheckInAlarm()
    }

    private fun sendSetGoalNotification() {
        if (!preferenceManager.setGoalsNotificationsAreEnabled()) {
            return
        }

        val notification = buildSetGoalNotification()
        notificationManager.notify(SET_GOAL_ID, notification)
        notificationAlarmManager.scheduleNextSetGoalsAlarm()
    }

    private fun buildCheckInNotification(): Notification {
        val intent = createCheckInPendingIntent()

        return NotificationCompat.Builder(app, GOAL_CHANNEL_ID)
                .setContentTitle(app.getString(R.string.notification_title_check_in))
                .setContentText(app.getString(R.string.notification_text_check_in))
                .setContentIntent(intent)
                .setChannelId(GOAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                .setAutoCancel(true)
                .build()
    }

    private fun buildSetGoalNotification(): Notification {
        val intent = createSetGoalsPendingIntent()

        return NotificationCompat.Builder(app, GOAL_CHANNEL_ID)
                .setContentTitle(app.getString(R.string.notification_title_set_goals))
                .setContentText(app.getString(R.string.notification_text_set_goals))
                .setContentIntent(intent)
                .setChannelId(GOAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp)
                .setAutoCancel(true)
                .build()
    }

    private fun createCheckInPendingIntent(): PendingIntent {
        val intent = Intent(app, MainActivity::class.java)
        intent.putExtra(MainActivity.KEY_START_SCREEN, MainActivity.VALUE_CHECK_IN)
        intent.setAction(MainActivity.VALUE_CHECK_IN)
        return PendingIntent.getActivity(app, CHECK_IN_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetGoalsPendingIntent(): PendingIntent {
        val intent = Intent(app, MainActivity::class.java)
        intent.putExtra(MainActivity.KEY_START_SCREEN, MainActivity.VALUE_SET_GOALS)
        intent.setAction(MainActivity.VALUE_SET_GOALS)
        return PendingIntent.getActivity(app, SET_GOAL_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object values {
        val NO_ID_SET = 0
        val CHECK_IN_ID = 1
        val SET_GOAL_ID = 2

        val TYPE = "key_type"
        val ACTION_SEND_NOTIFICATION = "action_send_notification"
    }
}
