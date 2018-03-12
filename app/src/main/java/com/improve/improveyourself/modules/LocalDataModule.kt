package com.improve.improveyourself.modules

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.improve.improveyourself.data.PreferenceManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.MyObjectBox
import com.improve.improveyourself.ui.ImproveApp
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class LocalDataModule() {
    @Provides
    @Singleton
    fun provideBoxStore(app: ImproveApp) = MyObjectBox.builder().androidContext(app).build()

    @Provides
    fun provideGoalBox(boxStore: BoxStore) = boxStore.boxFor(Goal::class.java)

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideSharedPreferences(app: ImproveApp,
                                 @Named("key_preferences") key: String): SharedPreferences {
        return app.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferenceManager(preferences: SharedPreferences, gson: Gson): PreferenceManager {
        return PreferenceManager(preferences, gson)
    }

    @Provides
    @Singleton
    @Named("key_preferences")
    fun provideSharedPreferencesKey() = "key_improve_shared_preferences"
}