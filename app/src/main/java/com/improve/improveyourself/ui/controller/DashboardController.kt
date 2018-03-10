package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.modules.TabContainerComponent
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.DashboardView
import com.improve.improveyourself.ui.view.DashboardViewImpl
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class DashboardController(var component: TabContainerComponent? = null) : Controller() {

    private lateinit var dashboardView: DashboardView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_dashboard, container, false)
        component!!.inject(this)
        dashboardView = DashboardViewImpl(view, this)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        mainRouter.hideActionBar()
        setCompletedCount()
    }

    private fun setCompletedCount() {
        val goalsCompleted = goalManager.getCompletedCount()
        when (goalsCompleted) {
            0L -> dashboardView.hideGoalCount()
            else -> {
                dashboardView.displayGoalCount()
                dashboardView.setGoalCount(goalsCompleted)
            }
        }
    }
}
