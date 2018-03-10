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
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.GoalListView
import com.improve.improveyourself.util.formatToDay
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class GoalListController(val date: String, var parentComponent: TabContainerComponent?) : Controller() {

    private lateinit var listComponent: GoalListComponent
    @Inject lateinit var tomorrowsGoalsView: GoalListView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter

    constructor(): this(Date().formatToDay(), null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_tomorrows_goals, container, false)
        listComponent = parentComponent!!.plus(GoalListModule(view,this))
        listComponent.inject(this)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    private fun loadGoals() {
        goalManager.loadGoalsFor(date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list -> tomorrowsGoalsView.displayList(list)})
    }

    fun onFabClicked() {
        mainRouter.launchNewGoal(date)
    }

}
