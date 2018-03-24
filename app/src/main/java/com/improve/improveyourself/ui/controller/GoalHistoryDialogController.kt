package com.improve.improveyourself.ui.controller

import android.view.View
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.GoalHistoryDialogView

/**
 * Created by konk3r on 2/7/18.
 */

class GoalHistoryDialogController(val goalHistoryDialogView: GoalHistoryDialogView,
                                  val goalManager: GoalManager, val mainRouter: MainRouter) {

    private lateinit var goal: Goal
    private var dismissListener: () -> Unit = {}

    fun bindGoal(goal: Goal) {
        this.goal = goal
        goalHistoryDialogView.setTitle(goal.title)
        goalHistoryDialogView.setType(goal.type)
        goalHistoryDialogView.setDescription(goal.steps)
        if (goal.steps.isEmpty()) {
            goalHistoryDialogView.removeDescription()
        } else {
            goalHistoryDialogView.displayDescription()
        }
        goalHistoryDialogView.setIcon(goal.getIconResource())
        if (goal.isCompleted) {
            goalHistoryDialogView.showCompletedIcon()
        } else {
            goalHistoryDialogView.hideCompletedIcon()
        }
    }

    fun getView(): View {
        return goalHistoryDialogView.getView()
    }

    fun onEditClicked() {
        mainRouter.launchEditGoal(goal)
        dismissListener.invoke()
    }

    fun setDismissListener(listener: () -> Unit) {
        dismissListener = listener
    }

}
