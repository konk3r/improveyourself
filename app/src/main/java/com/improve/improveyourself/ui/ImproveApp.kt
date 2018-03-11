package com.improve.improveyourself.ui

import android.app.Application
import com.improve.improveyourself.modules.AppComponent
import com.improve.improveyourself.modules.AppModule
import com.improve.improveyourself.modules.DaggerAppComponent
import com.improve.improveyourself.ui.notification.NotificationAlarmManager
import javax.inject.Inject

/**
 * Created by konk3r on 2/10/18.
 */
class ImproveApp : Application() {

    @Inject lateinit var notificationAlarmManager: NotificationAlarmManager

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        notificationAlarmManager.setup()
    }
}