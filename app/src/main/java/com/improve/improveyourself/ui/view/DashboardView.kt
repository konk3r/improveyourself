package com.improve.improveyourself.ui.view

/**
 * Created by konk3r on 2/11/18.
 */
interface DashboardView {
    fun hideGoalCount()
    fun displayGoalCount()
    fun setGoalCount(goalsCompleted: Long)
}