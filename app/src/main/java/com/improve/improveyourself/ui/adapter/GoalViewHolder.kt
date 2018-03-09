package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.improve.improveyourself.data.model.Goal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_goals.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalViewHolder(override val containerView: View?): RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(goal: Goal) {
        list_goal_title.text = goal.title
    }
}