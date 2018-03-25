package com.improve.improveyourself.ui.view

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.data.model.ListItem
import com.improve.improveyourself.ui.adapter.GoalHistoryAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_goal_history.*

/**
 * Created by konk3r on 2/11/18.
 */
class GoalHistoryViewImpl(override val containerView: View, val goalAdapter: GoalHistoryAdapter) :
        LayoutContainer, GoalHistoryView {

    init {
        view_goal_history_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = goalAdapter
        }
    }

    override fun displayList(list: MutableList<ListItem<Goal>>) {
        goalAdapter.setList(list)
    }

    override fun notifyUpdated(goal: ListItem<Goal>) {
        goalAdapter.onGoalUpdated(goal)
    }
}