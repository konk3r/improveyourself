package com.improve.improveyourself.ui.navigation

import java.util.*

/**
 * Created by konk3r on 3/8/18.
 */
interface MainRouter {
    fun launchNewGoal(date: Date)
    fun goBack()
    fun hideActionBar()
    fun showActionBar()
}