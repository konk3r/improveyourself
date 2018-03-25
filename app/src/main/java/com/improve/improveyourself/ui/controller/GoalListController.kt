package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.DateSelection
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.modules.GoalListComponent
import com.improve.improveyourself.modules.GoalListModule
import com.improve.improveyourself.ui.navigation.FabListener
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.navigation.ToolbarManager
import com.improve.improveyourself.ui.view.GoalListView
import com.improve.improveyourself.util.roundDateToDay
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by konk3r on 2/7/18.
 */

class GoalListController(var date: Date = Date().roundDateToDay()) :
        Controller(), FabListener {

    init {
        date = date.roundDateToDay()
    }

    val component by lazy { (parentController as TabContainerController).component }

    private lateinit var listComponent: GoalListComponent
    @Inject lateinit var goalsView: GoalListView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter
    @Inject lateinit var toolbarManager: ToolbarManager
    @Inject lateinit var dateSelection: List<DateSelection>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_goals, container, false)
        listComponent = component.plus(GoalListModule(view, this))
        listComponent.inject(this)
        goalsView.setController(this)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        setupSpinner()
        loadGoals()
    }

    private fun setupSpinner() {
        val titles = dateSelection.map { selection -> selection.title }
        val adapter = ArrayAdapter(activity, R.layout.list_goal_types, titles)

        toolbarManager.displayActionBar()
        toolbarManager.setSpinnerAdapter(adapter)
        toolbarManager.hideTitle()
        toolbarManager.displaySpinner()
        toolbarManager.setSpinnerSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(view: AdapterView<*>?) {
            }

            override fun onItemSelected(view: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                date = dateSelection.get(position).date.invoke()
                loadGoals()
            }
        })
        setupTitlePosition()
    }

    private fun setupTitlePosition() {
        for (selection in dateSelection) {
            val selectionDate = selection.date.invoke()
            if (selectionDate.time == date.time) {
                toolbarManager.setSpinnerPosition(dateSelection.indexOf(selection))
            }
        }
    }

    private fun loadGoals() {
        goalManager.loadGoalsFor(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list -> goalsView.displayList(list) })
    }

    override fun onFabClicked() {
        mainRouter.launchNewGoal(date)
    }

    fun onGoalLongClicked(goal: Goal) {
        mainRouter.launchEditGoal(goal)
    }

}
