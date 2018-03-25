package com.improve.improveyourself.ui.view

import java.util.*

/**
 * Created by konk3r on 2/11/18.
 */
interface CreateGoalView {
    fun setGoalTypes(types: MutableList<String>)
    fun displayGoalError()
    fun clearGoalError()
    fun setDate(date: Date)
    fun setTitle(title: String)
    fun setType(type: String)
    fun setSteps(steps: String)
    fun displayDateDialog(startYear: Int, startMonth: Int, startDay: Int)
    fun hideDeleteButton()
    fun displayDeleteButton()
    fun displayDeleteDialog()
}