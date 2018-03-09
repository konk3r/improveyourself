package com.improve.improveyourself.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.improve.improveyourself.R
import com.improve.improveyourself.modules.MainModule
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.controller.CreateGoalController
import com.improve.improveyourself.ui.controller.GoalListController
import com.improve.improveyourself.ui.controller.MainController
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.util.formatToDay
import com.improve.improveyourself.util.getTomorrowsDate
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainRouter {

    val Activity.app: ImproveApp get() = application as ImproveApp
    val component by lazy { app.component.plus(MainModule(this)) }
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, view_container, savedInstanceState)
        if (!router.hasRootController()) {
            val controller = MainController()
            router.setRoot(RouterTransaction.with(controller));
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack())
        super.onBackPressed()
    }

    override fun launchTodaysGoals() {
        val today = Date().formatToDay()
        router.pushController(RouterTransaction.with(GoalListController(today))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun launchTomorrowsGoals() {
        val tomorrow = Date().getTomorrowsDate().formatToDay()

        router.pushController(RouterTransaction.with(GoalListController(tomorrow))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun launchNewGoal(date: String) {
        router.pushController(RouterTransaction.with(CreateGoalController(date))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun goBack() {
        router.popCurrentController()
    }

}
