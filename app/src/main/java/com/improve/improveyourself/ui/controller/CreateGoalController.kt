package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.navigation.ToolbarManager
import com.improve.improveyourself.ui.view.CreateGoalView
import com.improve.improveyourself.ui.view.CreateGoalViewImpl
import com.improve.improveyourself.util.toDay
import com.improve.improveyourself.util.toMonth
import com.improve.improveyourself.util.toYear
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by konk3r on 2/7/18.
 */

class CreateGoalController(var date: Date = Date()) : Controller() {

    val component by lazy { (parentController as TabContainerController).component }
    private var goal: Goal? = null

    constructor(goal: Goal) : this(goal.date) {
        this.goal = goal
    }

    private lateinit var createGoalView: CreateGoalView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter
    @Inject lateinit var toolbarManager: ToolbarManager
    @field:[Inject Named("goal_types")]
    internal lateinit var types: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_create_goal, container, false)
        component.inject(this)
        createGoalView = CreateGoalViewImpl(view, this)
        createGoalView.setGoalTypes(types)
        createGoalView.setDate(date)
        setupToolbar()
        setupDeleteButton()

        if(goal != null) {
            setupGoal()
        }

        return view
    }

    private fun setupDeleteButton() {
        if (goal == null) {
            createGoalView.hideDeleteButton()
        } else {
            createGoalView.displayDeleteButton()
        }
    }

    private fun setupToolbar() {
        toolbarManager.displayActionBar()
        toolbarManager.hideSpinner()
        toolbarManager.displayTitle()
        if (goal == null) {
            toolbarManager.setTitle("Create goal")
        } else {
            toolbarManager.setTitle("Update goal")
        }
    }

    private fun setupGoal() {
        createGoalView.setTitle(goal!!.title)
        createGoalView.setType(goal!!.type)
        createGoalView.setSteps(goal!!.steps)
    }

    fun onFabClicked(type: String, title: String, steps: String) {
        createGoalView.clearGoalError()

        if (title.isEmpty()) {
            createGoalView.displayGoalError()
        } else {
            manageGoal(type, title, steps)
            mainRouter.goBack()
        }
    }

    private fun manageGoal(type: String, title: String, steps: String) {
        if (goal == null) {
            goal = Goal(type, title, date, steps)
        } else {
            goal!!.type = type
            goal!!.title = title
            goal!!.date = date
            goal!!.steps =steps
        }

        goalManager.storeGoal(goal!!)
    }

    fun onDateClicked() {
        createGoalView.displayDateDialog(date.toYear(), date.toMonth(), date.toDay())
    }

    fun onDateSelected(year: Int, month: Int, day: Int) {
        date = GregorianCalendar(year, month, day).time
        createGoalView.setDate(date)
    }

    fun onDeleteClicked() {
        createGoalView.displayDeleteDialog()
    }

    fun onDeleteConfirmationClicked() {
        goalManager.removeGoal(goal!!)
        mainRouter.goBack()
    }

}
