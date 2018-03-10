package com.improve.improveyourself.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.improve.improveyourself.R
import com.improve.improveyourself.modules.MainModule
import com.improve.improveyourself.ui.ImproveApp
import com.improve.improveyourself.ui.controller.TabContainerController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val Activity.app: ImproveApp get() = application as ImproveApp
    val component by lazy { app.component.plus(MainModule(this)) }
    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val controller = TabContainerController()

        router = Conductor.attachRouter(this, view_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(controller));
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack())
        super.onBackPressed()
    }

}
