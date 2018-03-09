package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import dagger.Module
import dagger.Provides

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class MainModule(val activity: MainActivity) {
    @Provides fun provideMainRouter(): MainRouter {
        return activity
    }
}