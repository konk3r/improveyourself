package com.improve.improveyourself.receivers

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.improve.improveyourself.R
import com.improve.improveyourself.data.PreferenceManager
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.notification.NotificationAlarmManager.IDs.GOAL_CHANNEL_ID
import javax.inject.Inject

/**
 * Created by konk3r on 3/10/18.
 */
class NotificationBroadcastReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationManager: NotificationManagerCompat
    @Inject lateinit var preferenceManager: PreferenceManager

    override fun onReceive(context: Context?, intent: Intent?) {
        val app = context?.applicationContext as ImproveApp
        app.component.inject(this)

        when (intent?.getIntExtra(TYPE, NO_ID_SET)) {
            CHECK_IN_ID -> {
                sendCheckInNotification(context)
            }

            SET_GOAL_ID -> {
                sendSetGoalNotification(context)
            }
        }
    }

    private fun sendCheckInNotification(context: Context) {
        if (!preferenceManager.checkInNotificationsAreEnabled()) {
            return
        }

        val notification = buildCheckInNotification(context)
        notificationManager.notify(CHECK_IN_ID, notification)
    }

    private fun sendSetGoalNotification(context: Context) {
        if (!preferenceManager.setGoalsNotificationsAreEnabled()) {
            return
        }

        val notification = buildSetGoalNotification(context)
        notificationManager.notify(SET_GOAL_ID, notification)
    }

    private fun buildCheckInNotification(context: Context?): Notification {
        val intent = createCheckInPendingIntent(context!!)

        return NotificationCompat.Builder(context, GOAL_CHANNEL_ID)
                .setContentTitle("Check in")
                .setContentText("How did you do yesterday? Check in to go over your goals.")
                .setContentIntent(intent)
                .setChannelId(GOAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_done_all_black_24dp)
                .setAutoCancel(true)
                .build()
    }

    private fun buildSetGoalNotification(context: Context): Notification {
        val intent = createSetGoalPendingIntent(context)

        return NotificationCompat.Builder(context, GOAL_CHANNEL_ID)
                .setContentTitle("Goal time")
                .setContentText("Now is as good a time as any, set your upcoming goals")
                .setContentIntent(intent)
                .setChannelId(GOAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp)
                .setAutoCancel(true)
                .build()
    }

    private fun createCheckInPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(context, CHECK_IN_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun createSetGoalPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java)
        return PendingIntent.getActivity(context, SET_GOAL_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    companion object values {
        val NO_ID_SET = 0
        val CHECK_IN_ID = 1
        val SET_GOAL_ID = 2

        val TYPE = "key_type"
    }
}
