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
import com.improve.improveyourself.ui.controller.MainController
import com.improve.improveyourself.ui.controller.TomorrowsGoalsController
import com.improve.improveyourself.ui.navigation.MainRouter
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun launchTomorrowsGoals() {
        router.pushController(RouterTransaction.with(TomorrowsGoalsController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun launchNewGoal() {
        router.pushController(RouterTransaction.with(CreateGoalController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    override fun goBack() {
        router.popCurrentController()
    }

}
