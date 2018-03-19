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
import com.improve.improveyourself.modules.GoalListComponent
import com.improve.improveyourself.modules.GoalListModule
import com.improve.improveyourself.modules.TabContainerComponent
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

class GoalListController(var date: Date = Date().roundDateToDay(), var parentComponent: TabContainerComponent? = null) :
        Controller(), FabListener {

    init {
        date = date.roundDateToDay()
    }

    private lateinit var listComponent: GoalListComponent
    @Inject lateinit var goalsView: GoalListView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter
    @Inject lateinit var toolbarManager: ToolbarManager
    @Inject lateinit var dateSelection: List<DateSelection>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_goals, container, false)
        listComponent = parentComponent!!.plus(GoalListModule(view, this))
        listComponent.inject(this)

        setupSpinner(container)

        return view
    }

    private fun setupSpinner(container: ViewGroup) {
        val titles = dateSelection.map { selection -> selection.title }
        val adapter = ArrayAdapter(container.context, R.layout.list_goal_types, titles)

        toolbarManager.displayActionBar()
        toolbarManager.setSpinnerAdapter(adapter)
        toolbarManager.hideTitle()
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

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    private fun loadGoals() {
        goalManager.loadGoalsFor(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list -> goalsView.displayList(list) })
    }

    override fun onFabClicked() {
        mainRouter.launchNewGoal(date)
    }

}
