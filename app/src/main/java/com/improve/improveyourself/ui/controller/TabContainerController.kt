package com.improve.improveyourself.ui.controller

import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.improve.improveyourself.R
import com.improve.improveyourself.R.id.main_toolbar
import com.improve.improveyourself.modules.TabContainerModule
import com.improve.improveyourself.ui.activity.MainActivity
import com.improve.improveyourself.ui.navigation.MainRouter
import com.improve.improveyourself.ui.view.TabContainerView
import com.improve.improveyourself.ui.view.TabContainerViewImpl
import com.improve.improveyourself.util.formatToDay
import java.util.*

/**
 * Created by konk3r on 2/7/18.
 */

class TabContainerController() : Controller(), MainRouter {

    private val DASHBOARD_POSITION = 1
    val component by lazy { (activity as MainActivity).component.plus(TabContainerModule(this)) }
    private lateinit var tabContainerView: TabContainerView
    private lateinit var supportActionBar: ActionBar
    private lateinit var bottomNavRouter: Router

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.view_main, container, false) as ViewGroup
        component.inject(this)
        tabContainerView = TabContainerViewImpl(view, this)
        bottomNavRouter = getChildRouter(view.findViewById(R.id.main_view_container))
        setupBottomNav(view)

        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        val toolbar: Toolbar = view.findViewById(main_toolbar)

        (activity as MainActivity).setSupportActionBar(toolbar)
        supportActionBar = (activity as MainActivity).supportActionBar!!

        val bottomNav = (view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView)
        if (!bottomNavRouter.hasRootController()) {
            val controller = DashboardController()
            bottomNavRouter.setRoot(RouterTransaction.with(controller));

            bottomNav.getMenu().getItem((DASHBOARD_POSITION)).isChecked = true
        }

        if (bottomNav.selectedItemId == DASHBOARD_POSITION) {
            supportActionBar.hide()
        }
    }

    private fun setupBottomNav(view: ViewGroup) {
        val bottomNavBar = view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView
        bottomNavBar.setOnNavigationItemSelectedListener({ item ->
            when (item.itemId) {
                R.id.action_dashboard -> launchDashboard()
                R.id.action_goals -> launchTodaysGoals()
            }
            true
        })
    }

    override fun onDestroyView(view: View) {
        val bottomNavBar = view.findViewById(R.id.main_bottom_navigation) as BottomNavigationView
        bottomNavBar.setOnNavigationItemSelectedListener(null)
        super.onDestroyView(view)
    }

    private fun launchDashboard() {
        supportActionBar.hide()
        val backstack = arrayListOf(RouterTransaction.with(DashboardController()))
        bottomNavRouter.setBackstack(backstack, null)
    }

    override fun launchTodaysGoals() {
        supportActionBar.show()
        val today = Date().formatToDay()
        val backstack = arrayListOf(RouterTransaction.with(GoalListController(today, component)))
        bottomNavRouter.setBackstack(backstack, null)
    }

    override fun launchNewGoal(date: String) {
        bottomNavRouter.pushController(RouterTransaction.with(CreateGoalController(date, component)))
    }

    override fun goBack() {
        if (!bottomNavRouter.popCurrentController()) {
            router.popCurrentController()
        }
    }

}
