package com.improve.improveyourself.modules

import com.improve.improveyourself.receivers.NotificationBroadcastReceiver
import com.improve.improveyourself.ui.ImproveApp
import dagger.Component
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
        NotificationModule::class,
        LocalDataModule::class,
        GoalModule::class))
interface AppComponent {
    fun inject(app: ImproveApp)
    fun inject(receiver: NotificationBroadcastReceiver)

    fun plus(mainModule: MainModule): MainComponent
}