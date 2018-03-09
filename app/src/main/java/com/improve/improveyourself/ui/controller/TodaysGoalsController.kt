package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.TodaysGoalsView
import com.improve.improveyourself.ui.view.TodaysGoalsViewImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class TodaysGoalsController : Controller() {

    val component by lazy { (activity as MainActivity).component }
    private lateinit var tomorrowsGoalsView: TodaysGoalsView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_tomorrows_goals, container, false)
        component.inject(this)
        tomorrowsGoalsView = TodaysGoalsViewImpl(view, this)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    private fun loadGoals() {
        goalManager.loadTodaysGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list -> tomorrowsGoalsView.displayList(list)})
    }

    fun onFabClicked() {
        mainRouter.launchNewGoal(Date())
    }

}
