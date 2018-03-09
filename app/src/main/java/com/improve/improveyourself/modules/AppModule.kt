package com.improve.improveyourself.modules

import com.improve.improveyourself.ui.ImproveApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class AppModule(val app: ImproveApp) {
    @Provides @Singleton fun provideApp() = app
}