package com.improve.improveyourself.ui.view

import android.app.DatePickerDialog
import android.view.View
import android.widget.ArrayAdapter
import com.afollestad.materialdialogs.MaterialDialog
import com.improve.improveyourself.R
import com.improve.improveyourself.ui.controller.CreateGoalController
import com.improve.improveyourself.util.formatToText
import com.improve.improveyourself.util.setAsGone
import com.improve.improveyourself.util.show
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_create_goal.*
import java.util.*

/**
 * Created by konk3r on 2/11/18.
 */
class CreateGoalViewImpl(override val containerView: View, val controller: CreateGoalController) :
        LayoutContainer, CreateGoalView {

    init {
        setupClickEvents()
    }

    private lateinit var types: MutableList<String>

    override fun setGoalTypes(types: MutableList<String>) {
        this.types = types
        create_goal_type.adapter = ArrayAdapter(containerView.context, R.layout.list_goal_types, types)
    }

    private fun setupClickEvents() {
        create_goal_fab.setOnClickListener({ _ ->
            run {
                val type = create_goal_type.selectedItem.toString()
                val title = create_goal_title_input.text.toString()
                val steps = create_goal_steps_input.text.toString()
                controller.onFabClicked(type, title, steps)
            }
        })

        create_goal_date.setOnClickListener({ _ -> controller.onDateClicked() })
        create_goal_button_delete.setOnClickListener({ _ -> controller.onDeleteClicked() })
    }

    override fun displayGoalError() {
        create_goal_title.error = "Goal title must be set"
    }

    override fun clearGoalError() {
        create_goal_title.error = null
    }

    override fun setDate(date: Date) {
        create_goal_date.text = date.formatToText()
    }

    override fun setTitle(title: String) {
        create_goal_title_input.setText(title)
    }

    override fun setType(type: String) {
        val position = types.indexOf(type)
        create_goal_type.setSelection(position)
    }

    override fun setSteps(steps: String) {
        create_goal_steps_input.setText(steps)
    }

    override fun displayDateDialog(startYear: Int, startMonth: Int, startDay: Int) {
        DatePickerDialog(containerView.context,
                { picker, year, month, day -> controller.onDateSelected(year, month, day) },
                startYear, startMonth, startDay)
                .show()
    }

    override fun hideDeleteButton() {
        create_goal_button_delete.setAsGone()
    }

    override fun displayDeleteButton() {
        create_goal_button_delete.show()
    }

    override fun displayDeleteDialog() {
        MaterialDialog.Builder(containerView.context)
                .title(R.string.delete_goal_dialog_title)
                .content(R.string.delete_goal_dialog_content)
                .negativeText(R.string.delete_goal_dialog_cancel)
                .positiveText(R.string.delete_goal_dialog_delete)
                .onPositive({_, _ -> controller.onDeleteConfirmationClicked()})
                .show()
    }

}