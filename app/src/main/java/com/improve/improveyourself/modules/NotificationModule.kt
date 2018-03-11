package com.improve.improveyourself.modules

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationManagerCompat
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.notification.NotificationAlarmManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class NotificationModule() {
    @Provides
    @Singleton
    fun provideNotificationAlarmManager(app: ImproveApp,
                                        alarmManager: AlarmManager,
                                        notificationManager: NotificationManager): NotificationAlarmManager {
        return NotificationAlarmManager(app, alarmManager, notificationManager)
    }

    @Provides
    fun provideAlarmManager(app: ImproveApp): AlarmManager {
        return app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    @Provides
    fun provideNotificationManager(app: ImproveApp): NotificationManager {
        return app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    fun provideNotificationManagerCompat(app: ImproveApp): NotificationManagerCompat {
        return NotificationManagerCompat.from(app)
    }
}