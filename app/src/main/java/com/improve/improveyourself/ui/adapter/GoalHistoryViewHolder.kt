package com.improve.improveyourself.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.improve.improveyourself.R
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.controller.GoalHistoryDialogController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_goal.*


/**
 * Created by konk3r on 3/8/18.
 */
class GoalHistoryViewHolder(override val containerView: View,
                            val goalHistoryDialogController: GoalHistoryDialogController) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        list_goal_container.setOnClickListener({ view -> displayGoalPopup(view.context) })
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

        when (goal.type) {
            Goal.PRODUCTIVE -> goal_history_update_type_icon.setImageResource(R.drawable.ic_fitness_center_black_24dp)
            Goal.SELF_CARE -> goal_history_update_type_icon.setImageResource(R.drawable.ic_hot_tub_black_24dp)
        }

        goal_history_update_completed.apply {
            if (goal.isCompleted) visibility = View.VISIBLE else visibility = View.INVISIBLE
        }
    }

    private fun displayGoalPopup(context: Context) {
        goalHistoryDialogController.bindGoal(goal)
        val dialog = MaterialDialog.Builder(context)
                .customView(goalHistoryDialogController.getView(), true)
                .positiveText(R.string.goal_history_dialog_done)
                .show()
        goalHistoryDialogController.setDismissListener({ dialog.dismiss() })
    }

}