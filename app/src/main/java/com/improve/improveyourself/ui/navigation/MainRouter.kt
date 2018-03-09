package com.improve.improveyourself.ui.navigation

/**
 * Created by konk3r on 3/8/18.
 */
interface MainRouter {
    fun launchTomorrowsGoals()
    fun launchNewGoal(date: String)
    fun goBack()
    fun launchTodaysGoals()
}