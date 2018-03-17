package com.improve.improveyourself.ui.view

import android.animation.Animator
import android.app.TimePickerDialog
import android.view.View
import com.improve.improveyourself.R
import com.improve.improveyourself.data.TimePair
import com.improve.improveyourself.ui.controller.DashboardController
import com.improve.improveyourself.util.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_dashboard.*

/**
 * Created by konk3r on 2/11/18.
 */
class DashboardViewImpl(override val containerView: View, val controller: DashboardController) :
        LayoutContainer, DashboardView {

    init {
        main_button_check_in_time.setOnClickListener({ view -> controller.onSetCheckInClicked() })
        main_button_set_goals_time.setOnClickListener({ view -> controller.onSetSetGoalsClicked() })
        main_button_cancel_check_in.setOnClickListener({ view -> controller.onCancelCheckInTimeClicked() })
        main_button_cancel_set_goals.setOnClickListener({ view -> controller.onCancelSetGoalsTimeClicked() })
    }

    override fun hideGoalCount() {
        main_input_goals_met.setAsGone()
        main_text_goals_met.setAsGone()
    }

    override fun displayGoalCount() {
        main_input_goals_met.show()
        main_text_goals_met.show()
    }

    override fun displayGoalCountIcon() {
        main_icon_goals_met.show()
    }

    override fun hideGoalCountIcon() {
        main_icon_goals_met.setAsGone()
    }

    override fun setGoalCount(goalsCompleted: Long) {
        main_input_goals_met.text = goalsCompleted.toString()
    }

    override fun displayCheckInDialog(defaultTime: TimePair) {
        val timePicker = TimePickerDialog(containerView.context,
                { picker, hours, minutes -> controller.onCheckInTimeSet(hours, minutes) },
                defaultTime.hour,
                defaultTime.minutes,
                false
        )

        timePicker.setTitle(R.string.dashboard_dialog_check_in_title)
        timePicker.show()
    }

    override fun displaySetGoalsDialog(defaultTime: TimePair) {
        val timePicker = TimePickerDialog(containerView.context,
                { picker, hours, minutes -> controller.onSetGoalsTimeSet(hours, minutes) },
                defaultTime.hour,
                defaultTime.minutes,
                false
        )

        timePicker.setTitle(R.string.dashboard_dialog_check_in_title)
        timePicker.show()
    }

    override fun displayCheckInCancelButton() {
        main_button_cancel_check_in.fadeIn()
    }

    override fun hideCheckInCancelButton() {
        main_button_cancel_check_in.hide()
    }

    override fun displayCheckInTime(checkInNotificationTime: TimePair) {
        val formattedTime = checkInNotificationTime.format()
        main_text_check_in_time.text = formattedTime
        main_text_check_in_time.fadeIn()
    }

    override fun hideCheckInTime() {
        main_text_check_in_time.hide()
    }

    override fun displaySetGoalsCancelButton() {
        main_button_cancel_set_goals.fadeIn()
    }

    override fun hideSetGoalsCancelButton() {
        main_button_cancel_set_goals.hide()
    }

    override fun displaySetGoalsTime(setGoalsNotificationTime: TimePair) {
        val formattedTime = setGoalsNotificationTime.format()
        main_text_set_goals_time.text = formattedTime
        main_text_set_goals_time.fadeIn()
    }

    override fun hideSetGoalsTime() {
        main_text_set_goals_time.hide()
    }

    override fun setCheckInTitleTextSet() {
        main_text_check_in.setText(R.string.main_goal_set_time_set)
    }

    override fun setCheckInTitleTextUnset() {
        main_text_check_in.setText(R.string.main_goal_set_time_unset)
    }

    override fun setSetGoalsTitleTextSet() {
        main_text_set_goals.setText(R.string.main_check_in_time_set)
    }

    override fun setSetGoalsTitleTextUnset() {
        main_text_set_goals.setText(R.string.main_check_in_time_unset)
    }

    override fun fadeOutSetGoalsCancelButton() {
        main_button_cancel_set_goals.fadeOut()
    }

    override fun fadeOutSetGoalsTime(animationEndListener: (Animator) -> Unit) {
        main_text_set_goals_time.fadeOut(endListener = animationEndListener)
    }

    override fun fadeOutCheckInCancelButton() {
        main_button_cancel_check_in.fadeOut()
    }

    override fun fadeOutCheckInTime(animationEndListener: (Animator) -> Unit) {
        main_text_check_in_time.fadeOut(endListener = animationEndListener)
    }

}