package com.improve.improveyourself.modules

import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by konk3r on 2/10/18.
 */
@Module
class GoalModule() {
    @Provides
    @Singleton
    fun provideUserManager(goalBox: Box<Goal>): GoalManager {
        return GoalManager(goalBox)
    }

    @Provides
    @Singleton
    @Named("goal_types")
    fun provideGoalTypes(): MutableList<String> {
        return arrayListOf("Productive", "Self Care") as MutableList<String>
    }
}