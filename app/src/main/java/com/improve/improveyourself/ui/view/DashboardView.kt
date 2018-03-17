package com.improve.improveyourself.ui.view

import com.improve.improveyourself.data.TimePair

/**
 * Created by konk3r on 2/11/18.
 */
interface DashboardView {
    fun hideGoalCount()
    fun displayGoalCount()
    fun setGoalCount(goalsCompleted: Long)
    fun displayCheckInDialog(defaultTime: TimePair)
    fun displaySetGoalsDialog(defaultTime: TimePair)
    fun displayCheckInCancelButton()
    fun hideCheckInCancelButton()
    fun displayCheckInTime(checkInNotificationTime: TimePair)
    fun hideCheckInTime()
    fun displaySetGoalsCancelButton()
    fun hideSetGoalsCancelButton()
    fun displaySetGoalsTime(setGoalsNotificationTime: TimePair)
    fun hideSetGoalsTime()
    fun setCheckInTitleTextSet()
    fun setCheckInTitleTextUnset()
    fun setSetGoalsTitleTextSet()
    fun setSetGoalsTitleTextUnset()
    fun displayGoalCounticon()
    fun hideGoalCountIcon()
}