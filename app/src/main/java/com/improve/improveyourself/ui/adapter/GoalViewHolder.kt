package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_goal.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalViewHolder(override val containerView: View, val goalBox: GoalManager) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        list_goal_container.setOnClickListener({ view -> toggleGoalCompletion() })
    }

    private lateinit var goal: Goal

    fun bind(goal: Goal) {
        this.goal = goal
        goal_history_update_title.text = goal.title
        goal_history_update_type.text = goal.type
        goal_history_update_type_icon.setImageResource(goal.getIconResource())
        if (goal.steps.isBlank()) {
            goal_history_update_text.text = goal.type
        } else {
            goal_history_update_text.text = goal.steps
        }

        goal_history_update_completed.apply {
            if (goal.isCompleted) visibility = View.VISIBLE else visibility = View.INVISIBLE
        }
    }

    private fun toggleGoalCompletion() {
        goal.isCompleted = !goal.isCompleted
        goalBox.storeGoal(goal)
        bind(goal)
    }
}