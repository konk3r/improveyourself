package com.improve.improveyourself.modules

import android.content.SharedPreferences
import com.improve.improveyourself.data.UserManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class UserModule() {
    @Provides
    @Singleton
    fun provideUserManager(sharedPrefs: SharedPreferences): UserManager {
        return UserManager(sharedPrefs)
    }
}