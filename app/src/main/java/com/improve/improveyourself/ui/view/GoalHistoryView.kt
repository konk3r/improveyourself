package com.improve.improveyourself.ui.view

import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.ListItem

/**
 * Created by konk3r on 2/11/18.
 */
interface GoalHistoryView {
    fun displayList(list: MutableList<ListItem<Goal>>)
    fun notifyUpdated(goal: ListItem<Goal>)
}