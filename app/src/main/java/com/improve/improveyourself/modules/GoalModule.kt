package com.improve.improveyourself.modules

import com.improve.improveyourself.data.GoalManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class GoalModule() {
    @Provides
    @Singleton
    fun provideUserManager(): GoalManager {
        return GoalManager()
    }

    @Provides
    @Singleton
    @Named("goal_types")
    fun provideGoalTypes(): MutableList<String> {
        return arrayListOf("Productive", "Self Care")
    }
}