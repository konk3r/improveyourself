package com.improve.improveyourself.ui.navigation

import com.improve.improveyourself.data.model.Goal
import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
interface MainRouter {
    fun launchNewGoal(date: Date)
    fun launchEditGoal(goal: Goal)
    fun goBack()
}