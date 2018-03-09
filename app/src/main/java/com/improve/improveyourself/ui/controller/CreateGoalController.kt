package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.data.GoalManager
import com.improve.improveyourself.data.model.Goal
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.CreateGoalView
import com.improve.improveyourself.ui.view.CreateGoalViewImpl
import com.improve.improveyourself.util.formatToDay
import com.improve.improveyourself.util.getTomorrowsDate
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by konk3r on 2/7/18.
 */

class CreateGoalController : Controller() {

    val component by lazy { (activity as MainActivity).component }
    private lateinit var createGoalView: CreateGoalView
    @Inject lateinit var goalManager: GoalManager
    @Inject lateinit var mainRouter: MainRouter
    @field:[Inject Named("goal_types")] internal lateinit var types: MutableList<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_create_goal, container, false)
        component.inject(this)
        createGoalView = CreateGoalViewImpl(view, this)
        createGoalView.setGoalTypes(types)

        return view
    }

    fun onCreateClicked(type: String, title: String, steps: String) {
        createGoalView.clearGoalError()

        if(title.isEmpty()) {
            createGoalView.displayGoalError()
        } else {
            val date = Date().getTomorrowsDate().formatToDay()
            goalManager.storeGoal(Goal(0, type, title, date, steps))
            mainRouter.goBack()
        }
    }

}
