package com.improve.improveyourself.ui.view

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.improve.improveyourself.ui.controller.MainController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_main.*

/**
 * Created by konk3r on 2/11/18.
 */
class MainViewImpl(override val containerView: View, val mainController: MainController) :
        LayoutContainer, MainView {
    init {
        setupClickEvents()
    }

    private fun setupClickEvents() {
        main_button_todays_goals.setOnClickListener { v -> mainController.onTodaysGoalsClicked() }
        main_button_tomorrows_goals.setOnClickListener { v -> mainController.onTomorrowsGoalsClicked() }
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

}