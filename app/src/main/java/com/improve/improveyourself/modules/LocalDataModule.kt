package com.improve.improveyourself.modules

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.MyObjectBox
import com.improve.improveyourself.ui.ImproveApp
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class LocalDataModule() {
    @Provides
    @Singleton
    fun provideBoxStore(app: ImproveApp): BoxStore {
        return MyObjectBox.builder().androidContext(app).build();
    }

    @Provides
    fun provideGoalBox(boxStore: BoxStore): Box<Goal> {
        return boxStore.boxFor(Goal::class.java)
    }
}