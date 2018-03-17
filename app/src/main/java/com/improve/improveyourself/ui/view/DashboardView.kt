package com.improve.improveyourself.ui.view

import android.animation.Animator
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
    fun displayGoalCountIcon()
    fun hideGoalCountIcon()
    fun fadeOutSetGoalsCancelButton()
    fun fadeOutSetGoalsTime(animationEndListener: (Animator) -> Unit = { animator -> {} })
    fun fadeOutCheckInCancelButton()
    fun fadeOutCheckInTime(animationEndListener: (Animator) -> Unit = { animator -> {} })
}