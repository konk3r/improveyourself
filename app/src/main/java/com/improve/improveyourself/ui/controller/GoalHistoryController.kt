package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.modules.GoalListComponent
import com.improve.improveyourself.modules.GoalListModule
import com.improve.improveyourself.modules.TabContainerComponent
import com.improve.improveyourself.ui.navigation.ToolbarManager
import com.improve.improveyourself.ui.view.GoalHistoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class GoalHistoryController(var parentComponent: TabContainerComponent? = null) :
        Controller() {

    private lateinit var listComponent: GoalListComponent
    @Inject lateinit var goalsView: GoalHistoryView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var toolbarManager: ToolbarManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_goal_history, container, false)
        listComponent = parentComponent!!.plus(GoalListModule(view))
        listComponent.inject(this)
        toolbarManager.displayActionBar()
        toolbarManager.setTitle("Goals")
        toolbarManager.displayTitle()
        toolbarManager.hideSpinner()

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    private fun loadGoals() {
        goalManager.loadPreviousGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list -> goalsView.displayList(list)})
    }

}
