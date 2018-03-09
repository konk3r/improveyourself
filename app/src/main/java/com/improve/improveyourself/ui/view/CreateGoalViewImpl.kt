package com.improve.improveyourself.ui.view

import android.view.View
import android.widget.ArrayAdapter
import com.improve.improveyourself.R
import com.improve.improveyourself.ui.controller.CreateGoalController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_goal_list.*

/**
 * Created by konk3r on 2/11/18.
 */
class CreateGoalViewImpl(override val containerView: View, val controller: CreateGoalController) :
        LayoutContainer, CreateGoalView {

    init {
        setupClickEvents()
    }

    override fun setGoalTypes(types: MutableList<String>) {
        create_goal_type.adapter = ArrayAdapter(containerView.context, R.layout.list_goal_types, types)
    }

    private fun setupClickEvents() {
        containerView.setOnClickListener({view -> run {
            val type = create_goal_type.selectedItem.toString()
            val title = create_goal_title_input.text.toString()
            val steps = create_goal_steps_input.text.toString()
            controller.onCreateClicked(type, title, steps)
        }})
    }

    override fun displayGoalError() {
        create_goal_title.error = "Goal title must be set"
    }

    override fun clearGoalError() {
        create_goal_title.error = null
    }

}