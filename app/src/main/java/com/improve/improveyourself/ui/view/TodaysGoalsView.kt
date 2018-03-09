package com.improve.improveyourself.ui.view

import com.improve.improveyourself.data.model.Goal

/**
 * Created by konk3r on 2/11/18.
 */
interface TodaysGoalsView {
    fun displayList(list: MutableList<Goal>)
}