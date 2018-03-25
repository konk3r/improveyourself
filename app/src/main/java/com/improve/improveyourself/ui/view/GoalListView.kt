package com.improve.improveyourself.ui.view

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.controller.GoalListController

/**
 * Created by konk3r on 2/11/18.
 */
interface GoalListView {
    fun displayList(list: MutableList<Goal>)
    fun setController(controller: GoalListController)
}