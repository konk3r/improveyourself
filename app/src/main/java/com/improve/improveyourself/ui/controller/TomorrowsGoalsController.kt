package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.TomorrowsGoalsView
import com.improve.improveyourself.ui.view.TomorrowsGoalsViewImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class TomorrowsGoalsController : Controller() {

    val component by lazy { (activity as MainActivity).component }
    private lateinit var tomorrowsGoalsView: TomorrowsGoalsView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_tomorrows_goals, container, false)
        component.inject(this)
        tomorrowsGoalsView = TomorrowsGoalsViewImpl(view, this)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        loadGoals()
    }

    private fun loadGoals() {
        goalManager.loadTomorrowsGoals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({list -> tomorrowsGoalsView.displayList(list)})
    }

    fun onFabClicked() {
        mainRouter.launchNewGoal()
    }

}
