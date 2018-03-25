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
        goalHistoryDialogView.setIcon(goal.getIconResource())


        setStepsVisibility()
        setCompletionVisibility()
    }

    private fun setStepsVisibility() {
        if (goal.steps.isEmpty()) {
            goalHistoryDialogView.removeDescription()
        } else {
            goalHistoryDialogView.displayDescription()
        }
    }

    private fun setCompletionVisibility() {
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

    fun onMarkIncompleteClicked() {
        goal.isCompleted = false
        goalManager.storeGoal(goal)
        setCompletionVisibility()
    }

    fun onMarkCompleteClicked() {
        goal.isCompleted = true
        goalManager.storeGoal(goal)
        setCompletionVisibility()
    }

}
