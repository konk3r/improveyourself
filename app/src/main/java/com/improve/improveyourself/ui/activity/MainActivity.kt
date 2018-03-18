package com.improve.improveyourself.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.improve.improveyourself.R
import com.improve.improveyourself.modules.MainModule
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.controller.TabContainerController
import com.improve.improveyourself.ui.controller.TabContainerController.Companion.SCREEN_CHECK_IN
import com.improve.improveyourself.ui.controller.TabContainerController.Companion.SCREEN_SET_GOALS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val Activity.app: ImproveApp get() = application as ImproveApp
    val component by lazy { app.component.plus(MainModule(this)) }
    private lateinit var router: Router
    private lateinit var controller: TabContainerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = createController()

        router = Conductor.attachRouter(this, view_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(controller));
        }
    }

    private fun createController(): TabContainerController {
        val type = getIntent().getStringExtra(KEY_START_SCREEN)
        return when (type) {
            VALUE_CHECK_IN -> TabContainerController(SCREEN_CHECK_IN)
            VALUE_SET_GOALS -> TabContainerController(SCREEN_SET_GOALS)
            else -> TabContainerController()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val type = intent?.getStringExtra(KEY_START_SCREEN)
        when (type) {
            VALUE_CHECK_IN -> controller.launchYesterdaysGoals()
            VALUE_SET_GOALS -> controller.launchSetGoals()
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack())
        super.onBackPressed()
    }

    companion object {
        val KEY_START_SCREEN = "key_open"

        val VALUE_CHECK_IN = "value_check_in"
        val VALUE_SET_GOALS = "value_set_goals"
    }

}
