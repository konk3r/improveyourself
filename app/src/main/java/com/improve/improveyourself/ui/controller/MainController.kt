package com.improve.improveyourself.ui.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.improve.improveyourself.R
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.MainView
import com.improve.improveyourself.ui.view.MainViewImpl
import javax.inject.Inject

/**
 * Created by konk3r on 2/7/18.
 */

class MainController() : Controller() {

    val component by lazy { (activity as MainActivity).component }
    private lateinit var mainView: MainView
    @Inject lateinit var mainRouter: MainRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_main, container, false)
        component.inject(this)
        mainView = MainViewImpl(view, this)

        return view
    }

    fun onTodaysGoalsClicked() {
        mainRouter.launchTodaysGoals()
    }

    fun onTomorrowsGoalsClicked() {
        mainRouter.launchTomorrowsGoals()
    }
}
