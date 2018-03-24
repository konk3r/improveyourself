package com.improve.improveyourself.ui.view

import android.view.View
import com.improve.improveyourself.ui.controller.GoalHistoryDialogController
import com.improve.improveyourself.util.hide
import com.improve.improveyourself.util.setAsGone
import com.improve.improveyourself.util.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dialog_goal_history_update.*

/**
 * Created by konk3r on 2/11/18.
 */
class GoalHistoryDialogViewImpl(override val containerView: View) :
        GoalHistoryDialogView, LayoutContainer {

    private lateinit var controller: GoalHistoryDialogController

    override fun setController(controller: GoalHistoryDialogController) {
        this.controller = controller
        goal_history_update_button_edit.setOnClickListener({ _ -> controller.onEditClicked() })
    }

    override fun setTitle(title: String) {
        goal_history_update_title.text = title
    }

    override fun setType(type: String) {
        goal_history_update_type.text = type
    }

    override fun setDescription(description: String) {
        goal_history_update_text.text = description
    }

    override fun removeDescription() {
        goal_history_update_text.setAsGone()
    }

    override fun displayDescription() {
        goal_history_update_text.show()
    }

    override fun setIcon(iconResource: Int) {
        goal_history_update_type_icon.setImageResource(iconResource)
    }

    override fun showCompletedIcon() {
        goal_history_update_completed.show()
    }

    override fun hideCompletedIcon() {
        goal_history_update_completed.hide()
    }

    override fun getView(): View {
        return containerView
    }

}