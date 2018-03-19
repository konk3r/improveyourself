package com.improve.improveyourself.modules

import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.DateSelection
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.util.addDay
import com.improve.improveyourself.util.roundDateToDay
import com.improve.improveyourself.util.subtractDay
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import java.util.*
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
        return arrayListOf(Goal.PRODUCTIVE, Goal.SELF_CARE)
    }

    @Provides
    @Singleton
    fun provideDateSelection(): List<DateSelection> {
        val yesterday = DateSelection("YESTERDAY",
                { Date().subtractDay().roundDateToDay() })
        val today = DateSelection("TODAY",
                { Date().roundDateToDay() })
        val tomorrow = DateSelection("TOMORROW",
                { Date().addDay().roundDateToDay() })

        return arrayListOf(yesterday, today, tomorrow)
    }
}