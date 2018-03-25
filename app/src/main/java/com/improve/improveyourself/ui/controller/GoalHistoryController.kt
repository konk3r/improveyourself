package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.modules.GoalListComponent
import com.improve.improveyourself.modules.GoalListModule
import com.improve.improveyourself.ui.navigation.ToolbarManager
import com.improve.improveyourself.ui.view.GoalHistoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class GoalHistoryController : Controller() {

    val component by lazy { (parentController as TabContainerController).component }

    private lateinit var listComponent: GoalListComponent
    private val disposables = CompositeDisposable()
    private var goals: MutableList<Goal> = ArrayList()
    @Inject lateinit var goalsView: GoalHistoryView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var toolbarManager: ToolbarManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_goal_history, container, false)
        listComponent = component.plus(GoalListModule(view))
        listComponent.inject(this)
        setupToolbar()
        setupGoalChangeResponse()

        return view
    }

    private fun setupToolbar() {
        toolbarManager.displayActionBar()
        toolbarManager.setTitle("Past goals")
        toolbarManager.displayTitle()
        toolbarManager.hideSpinner()
    }

    private fun setupGoalChangeResponse() {
        disposables.add(goalManager.getUpdateObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .filter({ goal -> goals.contains(goal) })
                .subscribe({goal -> goalsView.notifyUpdated(goal)}))
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

    private fun loadGoals() {
        goalManager.loadPreviousGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGoalsReceived)
    }

    private fun onGoalsReceived(goals: MutableList<Goal>) {
        this.goals = goals
        goalsView.displayList(goals)
    }

}
