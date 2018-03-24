package com.improve.improveyourself.ui.view

import android.view.View
import com.improve.improveyourself.ui.controller.GoalHistoryDialogController

/**
 * Created by konk3r on 2/11/18.
 */
interface GoalHistoryDialogView {
    fun setTitle(title: String)
    fun setType(type: String)
    fun setDescription(description: String)
    fun setIcon(iconResource: Int)
    fun showCompletedIcon()
    fun hideCompletedIcon()
    fun getView(): View
    fun removeDescription()
    fun displayDescription()
    fun setController(controller: GoalHistoryDialogController)
}