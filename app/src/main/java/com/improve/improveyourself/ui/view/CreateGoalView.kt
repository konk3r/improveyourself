package com.improve.improveyourself.ui.view

/**
 * Created by konk3r on 2/11/18.
 */
interface CreateGoalView {
    fun setGoalTypes(types: MutableList<String>)
    fun displayGoalError()
    fun clearGoalError()
}