package com.boofr.android.modules

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.improve.improveyourself.ui.ImproveApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class LocalDataModule() {
    private val PREFERENCE_KEY = "BOOFR_PREFS"

    @Provides
    @Singleton
    fun provideSharedPreferences(app: ImproveApp) : SharedPreferences {
        return app.getSharedPreferences(PREFERENCE_KEY, MODE_PRIVATE)
    }

}