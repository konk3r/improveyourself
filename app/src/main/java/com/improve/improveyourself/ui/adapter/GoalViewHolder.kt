package com.improve.improveyourself.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.improve.improveyourself.R
import com.improve.improveyourself.data.model.Goal
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_goal.*

/**
 * Created by konk3r on 3/8/18.
 */
class GoalViewHolder(override val containerView: View?): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(goal: Goal) {
        list_goal_title.text = goal.title

        when(goal.type) {
            Goal.PRODUCTIVE -> list_goal_type_logo.setImageResource(R.drawable.ic_fitness_center_accent_24dp)
            Goal.RELAXATION -> list_goal_type_logo.setImageResource(R.drawable.ic_hot_tub_accent_24dp)
        }

        list_goal_completed.apply {
            if (goal.isCompleted) visibility = View.VISIBLE else visibility = View.INVISIBLE
        }


    }
}