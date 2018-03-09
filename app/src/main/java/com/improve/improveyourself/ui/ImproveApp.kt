package com.improve.improveyourself.ui

import android.app.Application
import com.improve.improveyourself.modules.AppComponent
import com.improve.improveyourself.modules.AppModule
import com.improve.improveyourself.modules.DaggerAppComponent

/**
 * Created by konk3r on 2/10/18.
 */
class ImproveApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}