package com.improve.improveyourself.ui.view

import android.app.TimePickerDialog
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.improve.improveyourself.R
import com.improve.improveyourself.ui.controller.DashboardController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_dashboard.*

/**
 * Created by konk3r on 2/11/18.
 */
class DashboardViewImpl(override val containerView: View, val controller: DashboardController) :
        LayoutContainer, DashboardView {

    init {
        main_input_check_in_time.setOnClickListener({view -> controller.onSetCheckInClicked()})
        main_input_goal_set_time.setOnClickListener({view -> controller.onSetSetGoalsClicked()})
    }

    override fun hideGoalCount() {
        main_input_goals_met.visibility = GONE
        main_text_goals_met.visibility = GONE
    }

    override fun displayGoalCount() {
        main_input_goals_met.visibility = VISIBLE
        main_text_goals_met.visibility = VISIBLE
    }

    override fun setGoalCount(goalsCompleted: Long) {
        main_input_goals_met.text = goalsCompleted.toString()
    }

    override fun displayCheckInDialog() {
        val timePicker = TimePickerDialog(containerView.context,
                {picker, hours, minutes -> controller.setCheckInTime(hours, minutes)}
                ,12
                ,0
                ,false
        )

        timePicker.setTitle(R.string.dashboard_dialog_check_in_title)
        timePicker.show()
    }

    override fun displaySetGoalsDialog() {
        val timePicker = TimePickerDialog(containerView.context,
                {picker, hours, minutes -> controller.setSetGoalsTime(hours, minutes)}
                ,12
                ,0
                ,false
        )

        timePicker.setTitle(R.string.dashboard_dialog_check_in_title)
        timePicker.show()
    }

}