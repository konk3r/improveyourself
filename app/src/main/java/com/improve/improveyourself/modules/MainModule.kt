package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.activity.MainActivity
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class MainModule(val activity: MainActivity) {
    @Provides fun provideActivity() = activity
    @Provides fun provideSupportFragmentManager() = activity.supportFragmentManager
}